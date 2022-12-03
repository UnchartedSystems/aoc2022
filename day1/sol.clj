(require '[clojure.string :as str])

(last (sort (map #(apply + (map read-string %)) (take-nth 2 (partition-by #(= "" %) (clojure.string/split-lines (slurp "input.txt")))))))

(apply + (take-last 3 (sort (map #(apply + (map read-string %)) (take-nth 2 (partition-by #(= "" %) (clojure.string/split-lines (slurp "input.txt"))))))))


;; More readable
(let [split-strings   (clojure.string/split-lines (slurp "input.txt"))
      inventories     (take-nth 2 (partition-by #(= "" %) split-strings))
      sorted-calories (sort (map #(apply + (map read-string %)) inventories))]

  (last sorted-calories)                   ; Part 1
  (apply + (take-last 3 sorted-calories))) ; Part 2
