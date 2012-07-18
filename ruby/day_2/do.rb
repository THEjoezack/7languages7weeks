puts "Print the contents of an array of sixteen numbers, four numbers at a time, using just each. Now, do the same with each_slice in Enumerable."
puts "Each:"
counter = 1
(1..16).to_a.each do |n|
  print n
  print counter % 4 == 0 ? "\n" : " "
  counter += 1
end

puts

puts "Slice:"
(1..16).to_a.each_slice(4) { |slice| puts slice.join(' ') }

puts

puts "The Tree class was interesting, but it did not allow you to specify a new tree with a clean user interface. Let the initializer accept a nested structure of hashes."


puts

class Tree
  attr_accessor :children, :node_name

  def initialize hash, name = nil
    @children = []
    hash.each_pair do |k,v|
      @children.push Tree.new(v, k)
    end

    if name
      @node_name = name
    else #Unnamed node, need to pull up a level
      @node_name = @children.first.node_name
      @children = @children.first.children
    end
  end

  def visit_all(&block)
    visit &block
    children.each {|c| c.visit_all &block}
  end

  def visit(&block)
    block.call self
  end
end

ruby_tree = Tree.new({'grandpa' => { 'dad' => {'child 1' => {}, 'child 2' => {} }, 'uncle' => {'child 3' => {},'child 4' => {} } } })

puts "Visiting a node"
ruby_tree.visit {|node| puts node.node_name}

puts
puts "Visiting entire tree"
ruby_tree.visit_all {|node| puts node.node_name}


puts

puts "Write a simple grep that will print the lines of a file having any occurrences of a phrase anywhere in that line. You will need to do a simple regular expression match and read lines from a file. (This is surprisingly simple in Ruby.) If you want, include line numbers."

valid_arguments = ARGV.count == 2
file = valid_arguments ? ARGV[0].to_s : "do.rb"
expression = valid_arguments ? ARGV[1].to_s : "ARGV"
puts "Searching for #{expression} in #{file}"

File.open(file,'r').each_with_index do |line,line_number|
	puts "#{line_number}: #{line}" if line =~ /#{expression}/
end
