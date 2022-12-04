(require '[clojure.string :as str])
(require '[clojure.set :as set])

(defn uppercase-offset [s]
  (if  (= s (str/upper-case s)) 38 96))

(let [rucksacks (str/split-lines (slurp "input.txt"))
      ;; Part 1
      get-inventory  #(mapv set (partition (/ (count %) 2) (str/split % #"")))
      inventories     (mapv get-inventory rucksacks)
      get-copy        (fn [sets] (first (apply set/intersection sets)))
      copies          (mapv get-copy inventories)
      str->priority   (fn [s] (- (int (first s)) (uppercase-offset s)))
      result-1        (apply + (mapv str->priority copies))
      ;; Part 2
      rucksets        (partition 3 (mapv #(into #{} (str/split % #"")) rucksacks))
      badges          (mapv get-copy rucksets)
      result-2        (apply + (mapv str->priority badges))]

  [result-1 result-2])
