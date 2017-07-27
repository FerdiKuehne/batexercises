(ns coffee.core
  (:gen-class))

(defn fibo
  ([] nil)
  ([f1 f2 z]
   (if (= 0 z)
     (+ f1 f2)
     (fibo (+ f1 f2) f1 (dec z)))))

(def t 4)

;;Write a function that takes a string as an argument and searches for it on Bing and Google using the slurp function.
;;Your function should return the HTML of the first page returned by the search.
;;Update your function so it takes a second argument consisting of the search engines to use.

  ;; Create a new function that takes a search term and search engines as arguments, and returns a vector of the URLs from the first page of search results from each search engine.
 
(def listofengines {:google "https://www.google.de/#q=" :bing "https://www.bing.com/search?q=" :aol "https://suche.aol.de/aol/search?query=" :faz "http://www.faz.net/suche/?query="})

(defn mysearch
  [searchvalue searchengine]
  (let [http (str (get listofengines (keyword searchengine)) searchvalue)
        vectorlist []]
    (time (let [searchresult (future (slurp http))]
            (spit "search.html" @searchresult)))))

(mysearch "kaffee" "bing")

(mysearch "kaffee" "google")

(mysearch "kaffee" "aol")

(mysearch "kaffee" "faz")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "By the old and new gods!")
  (println "fib function without values:" (fibo))
  (println "fibo after"  t   "steps:" (fibo 1 1 t))
  (println "u got a coffe " (slurp "search.html")))

;;with future i define a task
