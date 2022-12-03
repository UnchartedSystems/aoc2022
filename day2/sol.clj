(require '[clojure.string :as str])
(require '[clojure.set :as set])

(def points {:rock      1
             :paper     2
             :scissors  3})

(def beats {:rock     :scissors
            :paper    :rock
            :scissors :paper})

(def loses (set/map-invert beats))

(def aliases {"A" :rock
              "X" :rock
              "B" :paper
              "Y" :paper
              "C" :scissors
              "Z" :scissors})

(defn interpret-order [pair]
  (let [p1 (get aliases (first pair))
        p2 (second pair)]
    (println p1 p2)
    (cond (= "Z" p2)  [p1 (p1 loses)]
          (= "X" p2)  [p1 (p1 beats)]
          :else       [p1 p1])))

(defn fight [p1 p2]
  (cond (= (p1 beats) p2) [(+ 6 (p1 points)) (p2 points)]
        (= (p1 loses) p2) [(p1 points) (+ 6 (p2 points))]
        :else             [(+ 3 (p1 points)) (+ 3 (p2 points))]))

(def pairs (mapv #(str/split % #" ") (str/split-lines (slurp "input.txt"))))
(def prob1 (mapv (fn [pair] (mapv #(get aliases %) pair)) pairs))
(def prob2 (mapv interpret-order pairs))

(apply + (mapv second (mapv #(apply fight %) prob1)))
(apply + (mapv second (mapv #(apply fight %) prob2)))
