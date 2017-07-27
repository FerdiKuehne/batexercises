(ns chapter-10.core
  (:gen-class))

(def vidi (atom {:result 0}))

(swap! vidi update-in [:result] inc)

@vidi
