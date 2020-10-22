import java.text.DecimalFormat;

/**
 * clasa odpowiada za konwersje walut
 *
 * @return wypisanie liczby z zapisem do 2 liczby po przecinku
 */
class Currency {

    Currency() {
    }

    String plnEuro(double plnEuro) {
        return new DecimalFormat("#.##").format(plnEuro * 0.22);
    }

    String euroPln(double euroPln) {
        return new DecimalFormat("#.##").format(euroPln * 4.55);
    }

}
