(ns day2
  (:require [clojure.string :as str]))


;; A for Rock
;; B for Paper
;; C for Scissors


;; X for Rock
;; Y for Paper
;; Z for Scissors

;; 1 for Rock
;; 2 for Paper
;; 3 for Scissors

;; 0 if you lost, 3 if the round was a draw, and 6 if you won


(def test-input
  "A Y
B X
C Z")

(def translation-map
  {"A" :rock
   "X" :rock
   "B" :paper
   "Y" :paper
   "C" :scissor
   "Z" :scissor})

(defn rules
  "Returns the winner, or nil if draw"
  [a b]
  (condp = (set [a b])
    #{:rock :scissor}  :rock
    #{:scissor :paper} :scissor
    #{:paper :rock}    :paper
    nil))

(rules :rock :scissor)  ;; => :rock
(rules :scissor :paper) ;; => :scissor
(rules :paper :rock)    ;; => :paper
(rules :paper :paper)   ;; => nil

(defn decide-on-winner [elf you]
  (let [winner (rules elf you)]
    (cond
      (nil? winner)  :draw
      (= you winner) :you
      (= elf winner) :elf)))

(decide-on-winner :rock :scissor)   ;; => :elf
(decide-on-winner :scissor :paper)  ;; => :elf
(decide-on-winner :paper :rock)     ;; => :elf
(decide-on-winner :rock :paper)     ;; => :you
(decide-on-winner :paper :paper)    ;; => :draw


(def points-map
  {:rock    1
   :paper   2
   :scissor 3
   :draw    3
   :elf     0
   :you     6})

(def input
  (slurp "./input.txt"))

;; problem 1
(->> input #_test-input
     (str/split-lines)
     (map #(str/split % #"\s"))
     (map #(mapv translation-map %))
     (map (fn [[elf you]]
            [(get points-map you) (get points-map (decide-on-winner elf you))]))
     (apply concat)
     (apply +))

;; X means you need to lose
;; Y means you need to draw
;; Z means you need to win

(def part2-map
  {"X" :elf
   "Y" :draw
   "Z" :you})

;; problem 2
(->> input #_test-input
     (str/split-lines)
     (map #(str/split % #"\s"))
     (map (fn [[elf x]]
            (let [elf-choose (get translation-map elf)]
              [elf-choose
               ;; find the first option that satisfies the needed outcome
               (->> (filter
                     (fn [your-option]
                       (let [outcome        (decide-on-winner elf-choose your-option)
                             needed-outcome (get part2-map x)]
                         (= outcome needed-outcome)))
                     [:paper :rock :scissor])
                    (first))])))
     (map (fn [[elf you]]
            [(get points-map you) (get points-map (decide-on-winner elf you))]))
     (apply concat)
     (apply +))
