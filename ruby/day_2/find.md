# Day 2 Find Exercises

*Find out how to access files with and without code blocks. What is the benefit of the code block?*
Block: Easier to read, don't have to remember to explicitly close the file
	File.open('test.txt','r').each { |line| puts |line| }

Non-Block: file = File.open('test.txt','r')
	while (line = file.gets)
	  puts line
	end
	file.close

*How would you translate a hash to an array?*
Not sure if translate is the right word for it, the types are not equivalent. You can however make an array of hashes. This method doesn't work well for nested hashes.
	{:b=>2, :c=>3, :a=>1}.each { |k,v| array.push({k => v}) }

*Can you translate arrays to hashes?*
Kinda, although translating a hash to array to hash is not equivalent to the first hash. Not a very good translation!
	array.each_index { |i| hash[i] = a[i] }

*Can you iterate through a hash?*
	{:b=>2, :c=>3, :a=>1}.each_index { |k,v| array.push({k => v}) }

*You can use Ruby arrays as stacks. What other common data structures do arrays support?*
Queue, List/Vector
