import java.text.DecimalFormat;

/**
 * clasa odpowiada za konwersje jednostki miary powierzchnio
 *
 * @return wypisanie liczby z zapisem do 2 liczby po przecinku
 */
class Surface {

    Surface() {
    }

    String m2Cal2(double m2Cal2) {
        return new DecimalFormat("#.##").format(m2Cal2 * 1550.00310);
    }

    String cal2M2(double cal2M2) {
        return new DecimalFormat("#.##").format(cal2M2 * 0.00065);
    }


}