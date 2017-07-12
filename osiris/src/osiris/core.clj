(ns osiris.core
  (:require [clojure.java.browse :as browse]
            [osiris.visu.svg :refer [xml]])
  (:gen-class))


(def godsofegypt [{:name "Amun"
                   :attr "A man with Wind"
                   :lat 10
                   :lng 6}
                  {:name "Amunet"
                   :attr "Wife of Amun, one of the creation goddesses."
                   :lat 10
                   :lng 10}
                  {:name "Anhur"
                   :attr "An Egyptian sky god and God of war.[3] His name meant"
                   :lat 6
                   :lng 6}
                  {:name "Anput"
                   :attr "Goddess of the seventeenth Nome of Upper Egypt"
                   :lat 6
                   :lng 10}])

(defn url
  [filename]
  (str "file:///"
       (System/getProperty "user.dir")
       "/"
       filename))

(defn template
  [contents]
  (str "<style>polyline { fill:none; stroke:#5881d8; stroke-width:3}</style>"
       contents))

(defn -main
  [& args]
  (let [filename "map.html"]
    (->> godsofegypt
         (xml 50 100)
         template
         (spit filename))
    (browse/browse-url (url filename))))


