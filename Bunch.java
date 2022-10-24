public class Bunch {
    private String bunchName;
    private  Hero[] arrH;
    private int hCount;
    public int getHCount() { return hCount; }
    public String getBunchName() { return bunchName; }
    public Hero[] getArrH() { return arrH; }

    public Bunch(String bunchName, int heroCount) {
        this.bunchName = bunchName;
        this.arrH = new Hero[heroCount];
        for (int i = 0; i < heroCount; i++) this.arrH[i] = null;
    }

    public boolean canAddHero(Hero hr) {
        int balance = 0;
        for (int i = 0; i < this.hCount; i++) {
            if (this.arrH[i].getIsGood()) {
                balance++;
            } else {
                balance -= 1;
            }
        }
        if (hr.getIsGood()) {
            balance++;
        } else {
            balance -= 1;
        }
        return balance >= -1 && balance <= 1;
    }

    public boolean addHero(Hero h) {
        if (canAddHero(h) && hCount < arrH.length) {
            arrH[hCount] = h;
            hCount++;
            return true;
        }
        return false;
    }

    public String stronger() {
        int sumGood = 0, sumBad = 0;
        for (int i = 0; i < this.hCount; i++) {
            if (this.arrH[i].getIsGood()) {
                sumGood += this.arrH[i].getStamina();
            } else sumBad += this.arrH[i].getStamina();
        }
        if (sumGood > sumBad) {
            return "good";
        } else if (sumGood < sumBad) {
            return "bad";
        } else {
            return "no winners";
        }
    }

    public String toString() {
        String str = "This bunch is called " + bunchName + ". It's heroes:";
        for (int i = 0; i < this.hCount; i++) {
            str += "\n    " + (i+1) + ". " + this.arrH[i].toString();
        }
        return str;
    }
}
