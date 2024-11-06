public class LifeGame {
    final int habitatSatir = 19;
    final int habitatSutun = 19;

    int hucreHabitat[][];
    int hucreHabitatTmp[][];

    public LifeGame() {
        // Habitatları ve pulsar desenini başlat
        hucreHabitat = new int[habitatSatir][habitatSutun];
        hucreHabitatTmp = new int[habitatSatir][habitatSutun];
        baslangicDeseniYukle();
    }

    private void baslangicDeseniYukle() {
        // Pulsar desenini doğru konumlarda başlat
        int[][] pulsarCoordinates = {
                {4, 5}, {4, 6}, {4, 7}, {4, 11}, {4, 12}, {4, 13},
                {5, 3}, {5, 8}, {5, 10}, {5, 15},
                {6, 3}, {6, 8}, {6, 10}, {6, 15},
                {7, 3}, {7, 8}, {7, 10}, {7, 15},
                {8, 5}, {8, 6}, {8, 7}, {8, 11}, {8, 12}, {8, 13},
                {10, 5}, {10, 6}, {10, 7}, {10, 11}, {10, 12}, {10, 13},
                {11, 3}, {11, 8}, {11, 10}, {11, 15},
                {12, 3}, {12, 8}, {12, 10}, {12, 15},
                {13, 3}, {13, 8}, {13, 10}, {13, 15},
                {14, 5}, {14, 6}, {14, 7}, {14, 11}, {14, 12}, {14, 13}
        };
        for (int[] coord : pulsarCoordinates) {
            hucreHabitat[coord[0]][coord[1]] = 1;
        }
    }

    public void drawHabitat() {
        for (int i = 1; i < habitatSatir - 1; i++) {
            for (int j = 1; j < habitatSutun - 1; j++) {
                System.out.print(hucreHabitat[i][j] == 1 ? "#" : "-");
            }
            System.out.println();
        }
    }

    public int komsuCanliSayisi(int satir, int sutun) {
        int canliKomsuSayisi = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (satir + i >= 0 && satir + i < habitatSatir && sutun + j >= 0 && sutun + j < habitatSutun) {
                    if (hucreHabitat[satir + i][sutun + j] == 1) canliKomsuSayisi++;
                }
            }
        }
        return canliKomsuSayisi;
    }

    public void newHabitatRule() {
        for (int i = 1; i < habitatSatir - 1; i++) {
            for (int j = 1; j < habitatSutun - 1; j++) {
                int canliKomsu = komsuCanliSayisi(i, j);
                if (hucreHabitat[i][j] == 1 && (canliKomsu < 2 || canliKomsu > 3)) {
                    hucreHabitatTmp[i][j] = 0;
                } else if (hucreHabitat[i][j] == 0 && canliKomsu == 3) {
                    hucreHabitatTmp[i][j] = 1;
                } else {
                    hucreHabitatTmp[i][j] = hucreHabitat[i][j];
                }
            }
        }
        copyHabitat();
    }

    public void copyHabitat() {
        for (int i = 1; i < habitatSatir - 1; i++) {
            System.arraycopy(hucreHabitatTmp[i], 1, hucreHabitat[i], 1, habitatSutun - 2);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LifeGame lg = new LifeGame();
        for (int i = 0; i < 20; i++) {
            lg.drawHabitat();
            lg.newHabitatRule();
            Thread.sleep(1500);
            System.out.println();
        }
    }
}
