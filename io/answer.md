Evaluate 1 + 1 and then 1 + "one". Is Io strongly typed or weakly typed?
Support your answer with code.

	You cannot add 1 and "one" together, because the interpreter will not try to coerce string to a number (and String doesn't have a slot for +) for you.

Is 0 true or false? What about the empty string? Is nil true or false? Support your answer with code.

	By default, everything in IO is true except for nil and false (unless you redefine it!)

	0 and true
	==> true

	0 and ""
	==> true

	nil and true
	==> false

How can you tell what slots a prototype supports?

	You can tell what slots an Object supports by using slotNames:

	Object slotNames
	==> list(yield, super, coroD...)

What is the difference between = (equals), := (colon equals), and ::= (colon colon equals)? When would you use each one?
	http://www.iolanguage.com/scm/io/docs/IoGuide.html#Syntax-Operators
	= Assigns a value to a slot, throws an exception if the slot doesn't already exist
	:= Creates a slot and assigns a value (even if it already exists)
	::= Creates slot, assigns a value and also creates a setter (http://stackoverflow.com/questions/5972327/whats-the-difference-between-newslot-and-setslot-in-the-io-language)

	I would create new slots with ::= when creating model type objects where I care about encapsulation. := when creating new slots, and = when I'm assigning new values. At first glance = might seem useless, but it seems like using = and := as intended gives better insight into the intention of the code.
