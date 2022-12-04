(require '[clojure.string :as str])


(map int (seq (char-array "abcdefghijklmnopqrstuvwxyz")))
(map int (seq (char-array (str/upper-case "abcdefghijklmnopqrstuvwxyz"))))

(defn uppercase-offset [s]
  (if  (= s (str/upper-case s)) 38 96))


(def inventories (mapv (fn [sack] (mapv #(into #{} %) (partition (/ (count sack) 2) (str/split sack #"")))) (str/split-lines (slurp "input.txt"))))
(def results (mapv #(some identity %) (mapv (fn [invs] (map #(if (contains? (second invs) % ) % false) (first invs))) inventories)))
(apply + (mapv (fn [s] (- (int (first s)) (uppercase-offset s))) results))

(def rucksacks (mapv #(into #{} (str/split % #"")) (str/split-lines (slurp "input.txt"))))
(apply + (mapv (fn [s] (- (int (first s)) (uppercase-offset s))) (mapv #(some identity %) (mapv (fn [group] (mapv #(if (= true (contains? (second group) %) (contains? (second (rest group)) %)) % false ) (first group))) (partition 3 rucksacks)))))
