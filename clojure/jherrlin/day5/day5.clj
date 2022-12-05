(ns day5
  (:require [clojure.string :as str]))


(defn rotate-matrix
  "Rotate matrix."
  [matrix]
  (->> matrix
       (apply map vector)
       (mapv identity)))

(defn str->int [s]
  (Integer. s))

(defn take-from-stack [m from nr]
  (let [stack (get m from)]
    [(take nr stack)
     (update m from #(drop nr %))]))

;; Remember Emacs remove-trailing-spaces
(def test-input
  "    [D]
[N] [C]
[Z] [M] [P]
 1   2   3

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2")

(def input
  (str/trim (slurp "./input.txt")))

(defn parse-input
  "Returns:
  {:crane-stacks {1 (\"N\" \"Z\"), 2 (\"D\" \"C\" \"M\"), 3 (\"P\")},
   :moves (1 2 1 3 1 3 2 2 1 1 1 2)}"
  [s]
  (let [[part1 part2] (-> s
                          (str/split #"\n\n"))
        cranes        (->> part1
                           (str/split-lines)
                           (drop-last 1)
                           (map (fn [row]
                                  (->> (re-seq #"(\[\w\]\s?|\s{3,4}\n?)" row)
                                       (map (comp #(str/replace % #"[\[\]\s]" "") first))
                                       (map #(when-not (str/blank? %) %)))))
                           (rotate-matrix)
                           (map #(remove nil? %))
                           (map-indexed vector)
                           (map (fn [[idx m]] [(inc idx) m]))
                           (into {}))
        moves         (->> part2
                           (str/split-lines)
                           (map (fn [row]
                                  (map str->int (re-seq #"\d{1,3}" row))))
                           (apply concat))]
    {:crane-stacks cranes
     :moves        moves}))

(defn rearange [f {:keys [crane-stacks moves]}]
  (loop [stacks              crane-stacks
         [nr from to & rest] moves]
    (if (nil? nr)
      stacks
      (let [[taken new-stack] (take-from-stack stacks from nr)]
        (recur
         (update new-stack to into (f taken))
         rest)))))

;; problem 1
(->> input #_test-input
     (parse-input)
     (rearange identity)
     (sort-by key)
     (map (comp first second))
     (apply str))
;; => "WHTLRMZRC"

(->> input #_test-input
     (parse-input)
     (rearange reverse)
     (sort-by key)
     (map (comp first second))
     (apply str))
;; => "GMPMLWNMG"
