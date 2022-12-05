(require '[clojure.string :as str])

;; destructuring and transforming input into pairs of ranges
(def lines (str/split-lines (slurp "input.txt")))
(def ranges (mapv #(partition 2 (mapv read-string (str/split % #"(,|-)"))) lines))

;; functions that check how ranges intersect
(def contains-range? #(or (and (<= %1 %2) (>= %3 %4))
                          (and (<= %2 %1) (>= %4 %3))))

(def overlaps-range? #(= false (< %3 %2) (< %4 %1)))

(def range-tests [contains-range? overlaps-range?])

;; returns solutions to 1 & 2 in a pair by mapping over range-tests
(mapv #(apply + (mapv (fn [[[x-1 x-2] [y-1 y-2]]] (if (% x-1 y-1 x-2 y-2) 1 0)) ranges)) range-tests)
