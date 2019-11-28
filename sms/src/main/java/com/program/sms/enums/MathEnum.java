package com.program.sms.enums;

public enum MathEnum {

    /**
     * 0
     */
    ZERO(0),
    /**
     * 1
     */
    ONE(1),
    /**
     * 2
     */
    TWO(2),
    /**
     * 3
     */
    THREE(3),
    /**
     * 4
     */
    FOUR(4),
    /**
     * 5
     */
    FIVE(5),
    /**
     * 6
     */
    SIX(6),
    /**
     * 7
     */
    SEVEN(7),
    /**
     * 8
     */
    EIGHT(8),
    /**
     * 9
     */
    NINE(9),
    /**
     * 10
     */
    TEN(10),
    /**
     * 19
     */
    NINETEEN(19),
    /**
     * 20
     */
    TWENTY(20),
    /**
     * 29
     */
    TWENTYNINE(29),
    /**
     * 30
     */
    THIRTY(30),
    /**
     * 39
     */
    THIRTYNINE(39),
    /**
     * 40
     */
    FORTY(40),
    /**
     * 49
     */
    FORTYNINE(49),
    /**
     * 50
     */
    FIFTY(50),
    /**
     * 59
     */
    FIFTYNINE(59),
    /**
     * 60
     */
    SIXTY(60),
    /**
     * 69
     */
    SIXTYNINE(69),
    /**
     * 70
     */
    SEVENTY(70),
    /**
     * 79
     */
    SEVENTYNINE(79),
    /**
     * 80
     */
    EIGHTY(80),
    /**
     * 89
     */
    EIGHTYNINE(89),
    /**
     * 90
     */
    NINETY(90),
    /**
     * 99
     */
    NINETYNINE(99);

    private int number;

    MathEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
}
