# chapter_10

Clojure Metaphysics: Atoms, Refs, Vars

* concurrency tools
* each types enables state-modifying operations
* clojure's data structures are immutable

## Atoms

* atoms provide a way to manage shared, synchronous, independent state
* ideal for managing the state of independent identities
* atoms refers to values
* safe transformation from one state to another
* to get an atom's current state -> dereference (@)
* update an atom -> swap! function
* swap! takes an atom and a function as arguments
* update an atom without checking -> reset! function
* atom share two features with the other refererence types -> Watches and Validators

## Refs

* update state of more than one identity simultaneously 
* using transaction semantics
* transaction have 3 features
1. atomic: all refs are updated or none of them
2. consistent: refs always appear to have valid states
3. isolated: executed serially -> similar to the compare-and-set semantics of atoms
* dosync initiates a transaction and defines its extent
* clojure uses Software Transactional Memory (STM) to implement that behavior 
* dereference refs just like dereference atoms
* modify refs -> alter function 
* alter a ref -> change isn't immediately visible outside of the current transaction
* alter function behavior
1. Reach outside the transaction and read the ref’s current state.
2. Compare the current state to the state the ref started with within the transaction.
3. If the two differ, make the transaction retry.
4. Otherwise, commit the altered ref state.
* commute function allows to update a ref's state within a transaction
* commute behavior
1. Reach outside the transaction and read the ref’s current state.
2. Run the commute function again using the current state.
3. Commit the result.
* only use commute if it is not possible to get in an invalid state
* commute is good to speed up programs

## Vars

* vars are associations between symbols and objects -> new vars with def
* dynamic binds -> (def ^:dynamic *name* value)
* to change dynamic bindings use binding
* dynamic vars are often used to name a resource that one or more functions target
* set! allows to convey information in to a function without having to return it as an argument
* thread-bound? function check if the var has been bound
* Per-Thread Binding -> manually created thread -> var will eval to original value
* altering the var root -> alter-var-root -> 2 arguments -> ref and function


## Watches and Validators

* watches -> check every alteration of a refererence types
* watch function -> four arguments
1. key -> use for reporting
2. reference being watched
3. previous state
4. new state 
* attach watch funtion to a reference -> add-watch function
* add-watch takes 3 arguments reference, key and watch function
* validators -> specify what states are allowed
* validator functions take 1 argument -> reference
* attach validators to an atom -> during creation 


## Exercises

Exercises are in core.clj

1. Create an atom with the initial value 0, use swap! to increment it a couple of times, and then dereference it.
2. Create a function that uses futures to parallelize the task of downloading random quotes fromhttp://www.braveclojure.com/random-quote using (slurp "http://www.braveclojure.com/random-quote"). The futures should update an atom that refers to a total word count for all quotes. The function will take the number of quotes to download as an argument and return the atom’s final value. Keep in mind that you’ll need to ensure that all futures have finished before returning the atom’s final value.
3. Create representations of two characters in a game. The first character has 15 hit points out of a total of 40. The second character has a healing potion in his inventory. Use refs and transactions to model the consumption of the healing potion and the first character healing.

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
