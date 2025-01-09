import javax.swing.*;
import java.awt.event.*;
import java.io.*;

// Not defteri uygulaması
public class NotDefteri {
    // Ana pencere (JFrame), metin alanı (JTextArea) ve dosya seçici (JFileChooser) tanımları
    private JFrame pencere;
    private JTextArea metinAlani;
    private JFileChooser dosyaSecici;

    // NotDefteri sınıfının kurucusu
    public NotDefteri() {
        // Ana pencere oluşturma
        pencere = new JFrame("Not Defteri");
        metinAlani = new JTextArea();
        dosyaSecici = new JFileChooser();

        // Pencere boyutu ve kapanış işlemi ayarlanıyor
        pencere.setSize(800, 600);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menü çubuğu oluşturma
        JMenuBar menuCubugu = new JMenuBar();

        // Menü ve menü öğeleri oluşturma
        JMenu dosyaMenusu = new JMenu("Dosya");
        JMenuItem yeniOgeler = new JMenuItem("Yeni");
        JMenuItem acOgeler = new JMenuItem("Aç");
        JMenuItem kaydetOgeler = new JMenuItem("Kaydet");
        JMenuItem cikisOgeler = new JMenuItem("Çıkış");

        // Menüye öğelerin eklenmesi
        dosyaMenusu.add(yeniOgeler);
        dosyaMenusu.add(acOgeler);
        dosyaMenusu.add(kaydetOgeler);
        dosyaMenusu.addSeparator(); // Ayırıcı
        dosyaMenusu.add(cikisOgeler);
        menuCubugu.add(dosyaMenusu);

        // Menü çubuğunun pencereye eklenmesi
        pencere.setJMenuBar(menuCubugu);

        // Metin alanına kaydırma çubuğu eklenmesi
        JScrollPane kaydirmaCubugu = new JScrollPane(metinAlani);
        pencere.add(kaydirmaCubugu);

        // Menü öğelerine olay dinleyiciler ekleniyor
        yeniOgeler.addActionListener(e -> yeniDosya());
        acOgeler.addActionListener(e -> dosyaAc());
        kaydetOgeler.addActionListener(e -> dosyaKaydet());
        cikisOgeler.addActionListener(e -> System.exit(0));

        // Pencereyi görünür yapma
        pencere.setVisible(true);
    }

    // Yeni bir dosya oluşturma işlemi
    private void yeniDosya() {
        // Metin alanını temizler
        metinAlani.setText("");
    }

    // Mevcut bir dosyayı açma işlemi
    private void dosyaAc() {
        // Kullanıcıdan dosya seçmesini ister
        int sonuc = dosyaSecici.showOpenDialog(pencere);
        if (sonuc == JFileChooser.APPROVE_OPTION) {
            File dosya = dosyaSecici.getSelectedFile(); // Seçilen dosyayı alır
            try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosya))) {
                metinAlani.setText(""); // Metin alanını temizler
                String satir;
                // Dosyayı satır satır okur ve metin alanına yazar
                while ((satir = okuyucu.readLine()) != null) {
                    metinAlani.append(satir + "\n");
                }
            } catch (IOException e) {
                // Hata durumunda kullanıcıya mesaj gösterir
                JOptionPane.showMessageDialog(pencere, "Dosya açılamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Yazılan notları dosyaya kaydetme işlemi
    private void dosyaKaydet() {
        // Kullanıcıdan bir dosya adı seçmesini ister
        int sonuc = dosyaSecici.showSaveDialog(pencere);
        if (sonuc == JFileChooser.APPROVE_OPTION) {
            File dosya = dosyaSecici.getSelectedFile(); // Seçilen dosyayı alır
            try (BufferedWriter yazici = new BufferedWriter(new FileWriter(dosya))) {
                yazici.write(metinAlani.getText()); // Metin alanındaki içeriği dosyaya yazar
                // Başarılı kaydetme durumunda mesaj gösterir
                JOptionPane.showMessageDialog(pencere, "Dosya başarıyla kaydedildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                // Hata durumunda kullanıcıya mesaj gösterir
                JOptionPane.showMessageDialog(pencere, "Dosya kaydedilemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Ana metod: Uygulamayı başlatır
    public static void main(String[] args) {
        // Swing uygulamalarını başlatır
        SwingUtilities.invokeLater(NotDefteri::new);
    }
}