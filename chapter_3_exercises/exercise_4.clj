;;Chapter 3 do-things
;;Exercise 4
;;Write a function, mapset, that works like map except the return value is a set

(defn mapset
  "function that decrease a value"
  [f value]
  (if (first value)
    (set (cons (f (first value))
               (mapset f (rest value))))
    (list)))

(mapset inc [1 1 2 2])
