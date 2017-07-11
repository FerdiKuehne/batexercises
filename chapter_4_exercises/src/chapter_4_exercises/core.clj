(ns chapter-4-exercises.core)

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

 (defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(def data (parse (slurp filename)))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))


(def mapData (mapify data))


(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

;;Start Exercise 1
;;Turn the result of your glitter filter into a list of names.
(defn listOfSuspects
  [filterrecords]
  (map :name filterrecords))


(def suspectData (listOfSuspects (glitter-filter 2 mapData)))
;;End Exercise 1

;;Start Exercise 2
;;Write a function, append, which will append a new suspect to your list of suspects.
(defn append
  [records newrecord]
  (-> (conj records newrecord) mapify))

(def newSuspect (listOfSuspects (append data ["Van Hellsing" "0"])))

(def appendv2 (into mapData (list {:name "a"  :id 2})))
;;End Exercise 2

;;Start Exercise 3
;;Write a function, validate, which will check that :name and :glitter-index
;;are present when you append. The validate function should accept two arguments:
;;a map of keywords to validating functions, similar to conversions, and the record to be validated.
(def mapofkeys {:name identity
                :glitter-index identity})

(defn validate
  [mok recoval]
     (map
        (fn [y]
          (map
           (fn [x]
             (if (false? (contains? mok (first x)))
               (conj x "not allowed keyword")
               true
               ))
           y))
        recoval))
;;End Exercise 3

;;Start Exercise 4
;;Write a function that will take your list of maps and convert it back to a CSV string.
;;You’ll need to use the clojure.string/join function.

(defn convertToCSV
  [record]
  (let [x record]
    (spit "suspects.csv" x)))

;;End Exercise 4


(validate mapofkeys appendv2)

(defn -main
  [& args]
  (println "Exercise 1 list of suspects \n" suspectData)
  (println "Exercise 2 append new suspect to list \n" newSuspect)
  (println "Exercise 3 validate list \n" (validate mapofkeys appendv2)))

