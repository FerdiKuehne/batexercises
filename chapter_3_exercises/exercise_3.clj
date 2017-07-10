;;Chapter 3 do-things
;;Exercise 3
;;Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction: 

(defn dec-maker
  "function that decrease a value"
  [value]
  #(- % value))

(def dec9 (dec-maker 9))
(dec9 10)
