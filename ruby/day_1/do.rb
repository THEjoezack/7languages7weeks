puts "Print the string \“Hello, world.\”"
puts "Hello, world."

puts

puts "For the string “Hello, Ruby,” find the index of the word \“Ruby.\”"
"Hello, Ruby.".index /Ruby/

puts

puts "Print your name ten times."
10.times { puts 'Joe' }

puts

puts "Print the string \“This is sentence number 1,\” where the number 1 changes from 1 to 10."
(1..10).each { |n| puts "This is sentence number #{n}" }

puts

puts "Run a Ruby program from a file."
puts "echo \"puts 'Hello, world'\" > test.rb; ruby test.rb"

puts

puts "Bonus problem: If you’re feeling the need for a little more, write a program that picks a random number. Let a player guess the number, telling the player whether the guess is too low or too high. (Hint: rand(10) will generate a random number from 0 to 9, and gets will read a string from the keyboard that you can translate to an integer.)"
min = 0
max = (2**10).to_i
magic_number = rand max

puts

puts "Guess what I'm thinking, pick a number between #{min} and #{max}"

puts

begin
  guess = gets.to_i
  puts "Too high, try again!" if guess > magic_number
  puts "Too low, try again!" if guess < magic_number
end while guess != magic_number

puts "You guessed correctly!"
