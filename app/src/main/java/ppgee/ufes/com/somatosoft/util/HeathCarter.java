package ppgee.ufes.com.somatosoft.util;

import java.io.Serializable;

public class HeathCarter implements Serializable {

    // Not used
    private String name;
    private String dataNascimento;
    private String sexo;
    private byte[] front;
    private byte[] side;

    // Used in calculus
    private double estaturaH;
    private double massaM;
    private double dobraCutaneaTR;
    private double dobraCutaneaSE;
    private double dobraCutaneaSB;
    private double dobraCutaneaPA;
    private double diametroDU;
    private double diametroDF;
    private double perimetroPB;
    private double perimetroPP;

    private boolean covid;
    private boolean cirurgia;
    private boolean diabetes;
    private boolean obesidade;
    private boolean hipertensao;
    private boolean cardiopatia;
    private boolean colesterolemia;
    private boolean dislipidemia;

    public HeathCarter() {}

    public HeathCarter(double estaturaH, double massaM, double dobraCutaneaTR, double dobraCutaneaSE, double dobraCutaneaSB, double dobraCutaneaPA, double diametroDU, double diametroDF, double perimetroPB, double perimetroPP) {
        this.estaturaH = estaturaH;
        this.massaM = massaM;
        this.dobraCutaneaTR = dobraCutaneaTR;
        this.dobraCutaneaSE = dobraCutaneaSE;
        this.dobraCutaneaSB = dobraCutaneaSB;
        this.dobraCutaneaPA = dobraCutaneaPA;
        this.diametroDU = diametroDU;
        this.diametroDF = diametroDF;
        this.perimetroPB = perimetroPB;
        this.perimetroPP = perimetroPP;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public double getEstaturaH() {
        return estaturaH;
    }

    public void setEstaturaH(double estaturaH) {
        this.estaturaH = estaturaH;
    }

    public double getMassaM() {
        return massaM;
    }

    public void setMassaM(double massaM) {
        this.massaM = massaM;
    }

    public double getDobraCutaneaTR() {
        return dobraCutaneaTR;
    }

    public void setDobraCutaneaTR(double dobraCutaneaTR) {
        this.dobraCutaneaTR = dobraCutaneaTR;
    }

    public double getDobraCutaneaSE() {
        return dobraCutaneaSE;
    }

    public void setDobraCutaneaSE(double dobraCutaneaSE) {
        this.dobraCutaneaSE = dobraCutaneaSE;
    }

    public double getDobraCutaneaSB() {
        return dobraCutaneaSB;
    }

    public void setDobraCutaneaSB(double dobraCutaneaSB) {
        this.dobraCutaneaSB = dobraCutaneaSB;
    }

    public double getDobraCutaneaPA() {
        return dobraCutaneaPA;
    }

    public void setDobraCutaneaPA(double dobraCutaneaPA) {
        this.dobraCutaneaPA = dobraCutaneaPA;
    }

    public double getDiametroDU() {
        return diametroDU;
    }

    public void setDiametroDU(double diametroDU) {
        this.diametroDU = diametroDU;
    }

    public double getDiametroDF() {
        return diametroDF;
    }

    public void setDiametroDF(double diametroDF) {
        this.diametroDF = diametroDF;
    }

    public double getPerimetroPB() {
        return perimetroPB;
    }

    public void setPerimetroPB(double perimetroPB) {
        this.perimetroPB = perimetroPB;
    }

    public double getPerimetroPP() {
        return perimetroPP;
    }

    public void setPerimetroPP(double perimetroPP) {
        this.perimetroPP = perimetroPP;
    }

    public void setCovid(boolean covid) {
        this.covid = covid;
    }

    public void setCirurgia(boolean cirurgia) {
        this.cirurgia = cirurgia;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public void setObesidade(boolean obesidade) {
        this.obesidade = obesidade;
    }

    public void setHipertensao(boolean hipertensao) {
        this.hipertensao = hipertensao;
    }

    public void setCardiopatia(boolean cardiopatia) {
        this.cardiopatia = cardiopatia;
    }

    public void setColesterolemia(boolean colesterolemia) {
        this.colesterolemia = colesterolemia;
    }

    public void setDislipidemia(boolean dislipidemia) {
        this.dislipidemia = dislipidemia;
    }

    public boolean isCovid() {
        return covid;
    }

    public boolean isCirurgia() {
        return cirurgia;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public boolean isObesidade() {
        return obesidade;
    }

    public boolean isHipertensao() {
        return hipertensao;
    }

    public boolean isCardiopatia() {
        return cardiopatia;
    }

    public boolean isColesterolemia() {
        return colesterolemia;
    }

    public boolean isDislipidemia() {
        return dislipidemia;
    }

    @Override
    public String toString() {
        return "HeathCarter{" +
                "estaturaH=" + estaturaH +
                ", massaM=" + massaM +
                ", dobraCutaneaTR=" + dobraCutaneaTR +
                ", dobraCutaneaSE=" + dobraCutaneaSE +
                ", dobraCutaneaSB=" + dobraCutaneaSB +
                ", dobraCutaneaPA=" + dobraCutaneaPA +
                ", diametroDU=" + diametroDU +
                ", diametroDF=" + diametroDF +
                ", perimetroPB=" + perimetroPB +
                ", perimetroPP=" + perimetroPP +
                '}';
    }

    public String toCSV() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("estaturah,massam,dobracutaneatr,dobracutanease,dobracutaneapa,diametrodu,diametrodf,perimetropb,perimetropp,xc,ip,pcb,pcp,ecto,meso,endo,x,y\n");
        stringBuilder.append(String.format("%s,", this.getName()));
        stringBuilder.append(String.format("%s,", this.getSexo()));
        stringBuilder.append(String.format("%s,", addParam(this.getMassaM())));
        stringBuilder.append(String.format("%s,", this.getDataNascimento()));
        stringBuilder.append(String.format("%s,", addParam(this.getEstaturaH())));
        stringBuilder.append(String.format("%s,", addParam(this.getDobraCutaneaTR())));
        stringBuilder.append(String.format("%s,", addParam(this.getDobraCutaneaSB())));
        stringBuilder.append(String.format("%s,", addParam(this.getDobraCutaneaSE())));
        stringBuilder.append(String.format("%s,", addParam(this.getDobraCutaneaPA())));
        stringBuilder.append(String.format("%s,", addParam(this.getDiametroDU())));
        stringBuilder.append(String.format("%s,", addParam(this.getDiametroDF())));
        stringBuilder.append(String.format("%s,", addParam(this.getPerimetroPB())));
        stringBuilder.append(String.format("%s,", addParam(this.getPerimetroPP())));
        stringBuilder.append(String.format("%s,", addParam(this.getXC())));
        stringBuilder.append(String.format("%s,", addParam(this.getIP())));
        stringBuilder.append(String.format("%s,", addParam(this.getPCB())));
        stringBuilder.append(String.format("%s,", addParam(this.getPCP())));
        stringBuilder.append(String.format("%s,", addParam(this.getEcto())));
        stringBuilder.append(String.format("%s,", addParam(this.getMeso())));
        stringBuilder.append(String.format("%s,", addParam(this.getEndo())));
        stringBuilder.append(String.format("%s,", addParam(this.getX())));
        stringBuilder.append(String.format("%s,", addParam(this.getY())));

        stringBuilder.append(String.format("%s,", addParam(this.isCovid())));
        stringBuilder.append(String.format("%s,", addParam(this.isCirurgia())));
        stringBuilder.append(String.format("%s,", addParam(this.isDiabetes())));
        stringBuilder.append(String.format("%s,", addParam(this.isObesidade())));
        stringBuilder.append(String.format("%s,", addParam(this.isHipertensao())));
        stringBuilder.append(String.format("%s,", addParam(this.isCardiopatia())));
        stringBuilder.append(String.format("%s,", addParam(this.isColesterolemia())));
        stringBuilder.append(String.format("%s,", addParam(this.isDislipidemia())));

        stringBuilder.append("\n,");


        return stringBuilder.toString();
    }

    private String addParam(boolean value) {
        return value ? "S" : "N";
    }

    private String addParam(Double value) {
        return String.format("%.04f", value).replace(".", ",");
    }

    private double XC;
    private double IP;
    private double PCB;
    private double PCP;
    private double ecto;
    private double meso;
    private double endo;
    private double X;
    private double Y;

    public void calculate() {
        XC = (dobraCutaneaTR + dobraCutaneaSB + dobraCutaneaSE) * 170.18/(estaturaH*100);
        IP = (estaturaH*100) / Math.cbrt(massaM);
        PCB = perimetroPB - dobraCutaneaTR/100;
        PCP = perimetroPP - dobraCutaneaPA/100;

        if (IP <= 38.25) {
            ecto = 0.1;
        } else if (IP > 38.25 && IP < 40.75) {
            ecto = (0.463 * IP) - 17.63;
        } else if (IP > 40.75) {
            ecto = (0.732 * IP) - 28.58;
        }

        //;0,858*K4 + 0,601*L4 + 0,188*Q4 + 0,161*R4 - 0,131*E4+4,5
        meso = 0.858*diametroDU + 0.601*diametroDF + 0.188*PCB + 0.161*PCP - 0.131*(estaturaH*100)+4.5;
        endo = -0.7182 + 0.1451*XC - 0.00068*Math.pow(XC, 2) + 0.0000014*Math.pow(XC,3);

        X = ecto - endo;
        Y = meso - endo - ecto;
    }

    public Double getEcto() {
        return ecto;
    }

    public Double getEndo() {
        return endo;
    }

    public Double getMeso() {
        return meso;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIP() {
        return IP;
    }

    public double getPCB() {
        return PCB;
    }

    public double getPCP() {
        return PCP;
    }

    public double getXC() {
        return XC;
    }

    public byte[] getFront() {
        return front;
    }

    public byte[] getSide() {
        return side;
    }

    public void setFront(byte[] front) {
        this.front = front;
    }

    public void setSide(byte[] side) {
        this.side = side;
    }
}

