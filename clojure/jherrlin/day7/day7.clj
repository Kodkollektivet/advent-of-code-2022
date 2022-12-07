(ns day7
  (:require [clojure.string :as str]))


(def test-input
  "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k")


(def input
  (slurp "./input.txt"))

(def regex-ls #"^\$ ls$")
(def regex-dir-in-dir #"^dir\s(.*)$")
(def regex-file-in-dir #"^(\d+)\s(.*)$")
(def regex-cd-to-root #"^\$ cd /$")
(def regex-cd-up #"^\$ cd \.\.$")
(def regex-cd-to-dir #"^\$ cd (.*)$")

(defn parse-row [s]
  (cond
    (re-matches regex-ls s)
    {:cmd :ls}

    (re-matches regex-dir-in-dir s)
    {:cmd :dir-in-dir
     :dir (last (re-find regex-dir-in-dir s))}

    (re-matches regex-file-in-dir s)
    (let [[size file] (rest (re-find regex-file-in-dir s))]
      {:cmd  :file-in-dir
       :size (Integer. size)
       :file file})

    (re-matches regex-cd-to-root s)
    {:cmd :cd-to-root}

    (re-matches regex-cd-up s)
    {:cmd :cd-up}

    (re-matches regex-cd-to-dir s)
    {:cmd :cd-to-dir
     :dir (last (re-find regex-cd-to-dir s))}))


(defmulti  cmd :cmd)
(defmethod cmd :cd-up       [{:as m}]             (update m :path (comp vec (partial drop-last 1))))
(defmethod cmd :cd-to-dir   [{:keys [dir] :as m}] (update m :path (fnil conj ["/"]) dir))
(defmethod cmd :cd-to-root  [{:as m}]             (assoc m :path ["/"]))
(defmethod cmd :default     [m] m)

(defn add-paths [ms]
  (->> ms
       (reduce
        (fn [acc m]
          (let [current-path (-> acc last :path)]
            (conj
             acc
             (cmd
              (assoc m :path current-path)))))
        [])))

(defn directory-sizes
  [s]
  (let [fs    (->> s str/split-lines (map parse-row) (add-paths))
        paths (->> fs (map :path) (set))
        ;; only files has the key :size
        files (->> fs (filter :size))]
    (->> paths
         (map
          (fn [path]
            {:path       path
             :total-size (->> files
                              (filter
                               ;; find files on the same path or that are
                               ;; further down in the directory hierarchy
                               #(= path (take (count path) (-> % :path))))
                              (map :size)
                              (apply +))})))))


;; problem 1
(->> (directory-sizes input #_test-input)
     (filter (comp #(>= 100000 %) :total-size))
     (map :total-size)
     (apply +))
;; => 1581595


;; problem 2
(let [total-disk-space   70000000
      needed-space       30000000
      dirs-with-sizes    (directory-sizes input #_test-input)
      used-space         (->> dirs-with-sizes
                              (map :total-size)
                              (apply max))
      current-free-space (- total-disk-space used-space)]
  (->> dirs-with-sizes
       (map :total-size)
       (sort)
       (filter #(> (+ current-free-space %) needed-space))
       (first)))
;; => 1544176
