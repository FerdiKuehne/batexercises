;;Chapter 3 do-things
;;Exercise 6
;;Create a function that generalizes symmetrize-body-parts and the function you created in Exercise 5.
;;The new function should take a collection of body parts and the number of matching body parts to add.
;;If you’re completely new to Lisp languages and functional programming, it probably won’t be obvious how to do this.
;;If you get stuck, just move on to the next chapter and revisit the problem later.

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])


(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})



;;Start of Exercise 6
(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size and a number how many parts are added"
  [asym-body-parts number-to-add]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (if (true? (clojure.string/includes? (:name part) "left-"))
                                       (if (even? number-to-add)
                                          (into (repeat (/ number-to-add 2) part) (repeat (/ number-to-add 2) (matching-part part)))
                                          (into (repeat (/ (+ 1 number-to-add) 2) part) (repeat (/ (- number-to-add 1) 2) (matching-part part))))
                                       (list part))))
          []
          asym-body-parts))

(symmetrize-body-parts asym-hobbit-body-parts 9)
