import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.*;

public class Cau4 {

    // Ban đầu đọc hết dữ liệu từ file (không lọc)
    public static Map<String, Integer> rawCorpus = new HashMap<>();
    public static Map<String, Integer> rawPairCorpus = new HashMap<>();
    public static int rawTotalWords = 0;

    // Tập V sau khi lọc các từ có tần số < 5
    public static Map<String, Integer> corpus = new HashMap<>();
    public static Map<String, Integer> dict = new HashMap<>();
    public static Map<Integer, String> reverseDict = new HashMap<>();
    public static Map<String, Integer> pairCorpus = new HashMap<>();

    public static Double[] probs;
    public static Double[][] condProbs;
    public static int totalWords;
    public static int totalBigrams;

    public static void readFile() {
        try {
            Vector<String> lines = new Vector<>();
            String filename = "D:\\github_repository\\IE303_LAB\\Lab1\\UIT-ViOCD.txt";
            File file = new File(filename);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                line = line.replace("\n", "").replace("\r", "").replace("\t", "");
                line = line.replaceAll("^\\s+", "").replaceAll("\\s+$", "").toLowerCase();
                lines.addElement(line);
            }
            fileReader.close();

            for (String line : lines) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = Normalizer.normalize(word, Normalizer.Form.NFC);
                    rawTotalWords++;
                    rawCorpus.put(word, rawCorpus.getOrDefault(word, 0) + 1);
                }
                for (int i = 0; i < words.length - 1; i++) {
                    String w1 = Normalizer.normalize(words[i], Normalizer.Form.NFC);
                    String w2 = Normalizer.normalize(words[i+1], Normalizer.Form.NFC);
                    String bigram = w1 + "_" + w2;
                    rawPairCorpus.put(bigram, rawPairCorpus.getOrDefault(bigram, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void buildVocabulary() {
        totalWords = 0;
        for (Map.Entry<String, Integer> entry : rawCorpus.entrySet()) {
            String word = entry.getKey();
            int freq = entry.getValue();
            if (freq >= 5) {
                corpus.put(word, freq);
                dict.put(word, dict.size());
                totalWords += freq;
            }
        }
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            reverseDict.put(entry.getValue(), entry.getKey());
        }
        totalBigrams = 0;
        for (Map.Entry<String, Integer> entry : rawPairCorpus.entrySet()) {
            String[] parts = entry.getKey().split("_");
            if (parts.length != 2) continue;
            String w1 = parts[0], w2 = parts[1];
            if (corpus.containsKey(w1) && corpus.containsKey(w2)) {
                pairCorpus.put(entry.getKey(), entry.getValue());
                totalBigrams += entry.getValue();
            }
        }
    }

    public static void calcPobsSingleWord() {
        probs = new Double[dict.size()];
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            String word = entry.getKey();
            int freq = corpus.get(word);
            int index = entry.getValue();
            probs[index] = (double) freq / totalWords;
        }
    }

    public static void calcProbConditionalPair() {
        int vocabSize = dict.size();
        condProbs = new Double[vocabSize][vocabSize];
        for (int i = 0; i < vocabSize; i++) {
            Arrays.fill(condProbs[i], 0.0);
        }

        for (Map.Entry<String, Integer> entry : pairCorpus.entrySet()) {
            String[] parts = entry.getKey().split("_");
            if (parts.length != 2) continue;
            String w1 = parts[0], w2 = parts[1];
            if (!dict.containsKey(w1) || !dict.containsKey(w2)) continue;
            int i = dict.get(w1);
            int j = dict.get(w2);
            int bigramFreq = entry.getValue();
            int w1Freq = corpus.get(w1);
            double pConditional = ((double) bigramFreq * totalWords) / (w1Freq * totalBigrams);
            condProbs[i][j] = pConditional;
        }
    }

    public static String getMostLikelyNextWord(int wordIndex) {
        if (wordIndex < 0 || wordIndex >= condProbs.length) {
            return null;
        }
        double maxProb = 0.0;
        int nextIndex = -1;
        for (int j = 0; j < condProbs[wordIndex].length; j++) {
            if (condProbs[wordIndex][j] > maxProb) {
                maxProb = condProbs[wordIndex][j];
                nextIndex = j;
            }
        }
        return nextIndex != -1 ? reverseDict.get(nextIndex) : null;
    }

    public static String generateSentence(String startWord) {
        startWord = Normalizer.normalize(startWord.trim(), Normalizer.Form.NFC);
        if (!dict.containsKey(startWord)) {
            return "Từ không có trong tập dữ liệu.";
        }

        StringBuilder sentence = new StringBuilder(startWord);
        String currentWord = startWord;
        for (int i = 0; i < 5; i++) {
            int idx = dict.get(currentWord);
            String nextWord = getMostLikelyNextWord(idx);
            if (nextWord == null) break;
            sentence.append(" ").append(nextWord);
            currentWord = nextWord;
        }
        return sentence.toString();
    }

    public static void main(String[] args) {
        readFile();
        buildVocabulary();
        calcPobsSingleWord();
        calcProbConditionalPair();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ bắt đầu: ");
        String inputWord = scanner.nextLine().toLowerCase();
        System.out.println("Câu sinh ra: " + generateSentence(inputWord));
    }
}
