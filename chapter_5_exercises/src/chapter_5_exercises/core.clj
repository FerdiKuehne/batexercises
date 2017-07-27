(ns chapter-5-exercises.core)


(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)

(c-str character)

(c-dex character)

;;1. You used (comp :intelligence :attributes) to create a function that returns
;;a character’s intelligence. Create a new function, attr,
;;that you can call like (attr :intelligence) and that does the same thing.
(defn attr [key] (-> character :attributes key))

(attr :intelligence)

;;2.Implement the comp function.
;;Takes a set of functions and returns a fn that is the composition of those fns.
;;a function that combines 2 functions
;;exp ((comp str +) 21 21)

;;4.Exercises
;;Look up and use the update-in function.
;;(update-in m ks f & args)
(def a {:name "a" :age 0})

(update-in a [:age] + 10 10)

;;5.Exercises
;;Implement update-in

(defn add2
  [x]
  (+ x 2))


(defn myfunc
  [y x]
  (+ (add2 y) (add2 x)))

(myfunc 2 3)

(defn -main
  [& args]
  (println "Chapter 5 Functional Programming!")
  (println "Exercises 2 combining 2 functions str + :\n" '((comp str +) 21 21) "->" ((comp str +) 21 21)))
