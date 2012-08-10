"Run an Io program from a file.\n" print
"echo '\"Hello World!\" print' > test.io; io test.io" print
"\n" print
"\n" print
"Execute the code in a slot given it's name\n" print
printAlias := String getSlot("print")
"There's some code here that gets the slot for the String method \"print\", then turns around and uses the alias to print this line" printAlias
"\n" print
