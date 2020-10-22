class Menu {

    /**
     * Klasa menu umożliwia wywołąnie odpowiedniej funkcji
     *
     * @param int    zmienna która steruje menu
     * @param double pobierana wartość - konwersacja
     */
    Menu() {
    }

    String menu(int option, double value) {
        Currency currency = new Currency();
        Surface surface = new Surface();
        Weight weight = new Weight();
        String score = "";

        switch (option) {
            case 0:
                score = currency.plnEuro(value);
                break;
            case 1:
                score = currency.euroPln(value);
                break;
            case 2:
                score = surface.m2Cal2(value);
                break;

            case 3:
                score = surface.cal2M2(value);
                break;

            case 4:
                score = weight.kgUncja(value);
                break;

            case 5:
                score = weight.uncjaKg(value);
                break;

            default:
                break;
        }
        return score;
    }
}