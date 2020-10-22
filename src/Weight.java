import java.text.DecimalFormat;

/**
 * clasa odpowiada za konwersje jednostki miary waga
 *
 * @return wypisanie liczby z zapisem do 2 liczby po przecinku
 */
class Weight {
    Weight() {
    }

    String kgUncja(double kgUncja) {
        return new DecimalFormat("#.##").format(kgUncja * 35.274);
    }

    String uncjaKg(double uncjaKg) {
        return new DecimalFormat("#.##").format(uncjaKg * 0.0283);
    }


}