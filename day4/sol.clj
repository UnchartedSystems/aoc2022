(require '[clojure.string :as str])

(def lines (str/split-lines (slurp "input.txt")))
(def pairs (mapv (fn [line] (partition 2 (mapv read-string (str/split line #"(,|-)")))) lines))
(def ranges (mapv (fn [ranges] ()) pairs))
