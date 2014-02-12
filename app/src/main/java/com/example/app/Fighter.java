package com.example.app;

import android.graphics.Bitmap;

public final class Fighter extends ModelDefinition {
    private static final short drawOrder[]={
            0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,
            45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,
            75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,
            105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,
            120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,
            135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,
            150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,
            165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,
            180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,
            195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,
            210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,
            225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,
            240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,
            255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,
            270,271,272,273,274,275,276,277,278,279,280,281,282,283,284,
            285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,
            300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,
            315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,
            330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,
            345,346,347
    };
    private static final float vertices[]={
            0.000000f,-1.665000f,-0.301667f,
            0.200000f,-2.664999f,-0.201667f,
            0.000000f,-2.664999f,-0.201667f,
            0.000000f,-0.065000f,-0.301667f,
            0.500000f,-0.665000f,-0.101667f,
            0.000000f,-0.765000f,-0.301667f,
            0.300000f,1.335000f,-0.201667f,
            0.000000f,-0.065000f,-0.301667f,
            0.000000f,1.035000f,-0.201667f,
            0.000000f,-0.765000f,-0.301667f,
            0.700000f,-1.265000f,-0.201667f,
            0.000000f,-1.665000f,-0.301667f,
            0.200000f,-1.665000f,-0.001667f,
            0.000000f,-1.965000f,0.098333f,
            0.000000f,-2.365000f,-0.101667f,
            0.700000f,-0.165000f,-0.001667f,
            1.500000f,-0.265000f,-0.301667f,
            1.400000f,-0.265000f,-0.201667f,
            0.500000f,1.335000f,-0.001667f,
            0.600000f,1.035000f,-0.101667f,
            0.300000f,1.335000f,-0.201667f,
            0.100000f,1.335000f,-0.001667f,
            0.000000f,1.035000f,-0.201667f,
            0.000000f,1.135000f,-0.001667f,
            0.700000f,-1.265000f,-0.201667f,
            0.000000f,-1.165000f,0.098333f,
            0.200000f,-1.665000f,-0.001667f,
            0.200000f,-2.664999f,-0.201667f,
            0.000000f,-2.365000f,-0.101667f,
            0.000000f,-2.664999f,-0.201667f,
            0.200000f,-2.664999f,-0.201667f,
            0.200000f,-1.665000f,-0.001667f,
            0.000000f,-2.365000f,-0.101667f,
            0.500000f,-0.665000f,-0.101667f,
            0.600000f,-0.065000f,-0.101667f,
            0.700000f,-0.165000f,-0.001667f,
            0.200000f,-2.664999f,-0.201667f,
            0.000000f,-1.665000f,-0.301667f,
            0.700000f,-1.265000f,-0.201667f,
            1.500000f,-0.265000f,-0.301667f,
            1.700000f,-0.765000f,-0.301667f,
            1.400000f,-0.765000f,-0.301667f,
            0.000000f,-1.965000f,0.098333f,
            0.200000f,-1.665000f,-0.001667f,
            0.000000f,-1.165000f,0.098333f,
            1.500000f,-0.265000f,-0.301667f,
            0.600000f,-0.065000f,-0.101667f,
            0.600000f,1.035000f,-0.101667f,
            0.200000f,-1.665000f,-0.001667f,
            0.200000f,-2.664999f,-0.201667f,
            0.700000f,-1.265000f,-0.201667f,
            0.500000f,-0.665000f,-0.101667f,
            0.000000f,-0.165000f,0.198333f,
            0.000000f,-1.165000f,0.098333f,
            0.700000f,-0.165000f,-0.001667f,
            0.500000f,-0.165000f,0.298333f,
            0.000000f,-0.165000f,0.198333f,
            0.000000f,1.135000f,0.098333f,
            0.100000f,1.335000f,-0.001667f,
            0.000000f,1.135000f,-0.001667f,
            0.600000f,1.035000f,-0.001667f,
            0.500000f,1.335000f,-0.001667f,
            0.400000f,1.035000f,0.198333f,
            0.700000f,-0.165000f,-0.001667f,
            0.500000f,0.435000f,0.298333f,
            0.500000f,-0.165000f,0.298333f,
            0.600000f,0.735000f,0.598333f,
            0.300000f,1.035000f,0.198333f,
            0.400000f,0.435000f,0.298333f,
            0.400000f,0.435000f,0.298333f,
            0.500000f,-0.165000f,0.298333f,
            0.500000f,0.435000f,0.298333f,
            0.000000f,-0.165000f,0.198333f,
            0.500000f,-0.665000f,-0.101667f,
            0.700000f,-0.165000f,-0.001667f,
            0.400000f,0.435000f,0.298333f,
            0.000000f,1.135000f,0.098333f,
            0.000000f,0.435000f,0.198333f,
            0.500000f,0.435000f,0.298333f,
            0.600000f,1.035000f,-0.001667f,
            0.400000f,1.035000f,0.198333f,
            0.600000f,1.035000f,0.598333f,
            0.400000f,1.035000f,0.198333f,
            0.300000f,1.035000f,0.198333f,
            0.500000f,0.435000f,0.298333f,
            0.600000f,0.735000f,0.598333f,
            0.400000f,0.435000f,0.298333f,
            0.100000f,1.335000f,-0.001667f,
            0.000000f,1.135000f,0.098333f,
            0.300000f,1.335000f,0.198333f,
            1.700000f,-0.765000f,-0.301667f,
            1.500000f,-0.265000f,-0.301667f,
            2.000000f,0.135000f,-0.301667f,
            1.400000f,-0.265000f,-0.201667f,
            1.700000f,-0.765000f,-0.301667f,
            2.000000f,0.135000f,-0.301667f,
            0.600000f,1.035000f,-0.101667f,
            0.500000f,1.335000f,-0.001667f,
            0.600000f,1.035000f,-0.001667f,
            0.300000f,1.335000f,0.198333f,
            0.000000f,1.135000f,0.098333f,
            0.300000f,1.035000f,0.198333f,
            0.500000f,1.335000f,-0.001667f,
            0.100000f,1.335000f,-0.001667f,
            0.300000f,1.335000f,0.198333f,
            0.100000f,1.335000f,-0.001667f,
            0.500000f,1.335000f,-0.001667f,
            0.300000f,1.335000f,-0.201667f,
            0.300000f,1.335000f,0.198333f,
            0.400000f,1.035000f,0.198333f,
            0.500000f,1.335000f,-0.001667f,
            0.000000f,1.035000f,-0.201667f,
            0.100000f,1.335000f,-0.001667f,
            0.300000f,1.335000f,-0.201667f,
            0.000000f,-0.065000f,-0.301667f,
            0.600000f,1.035000f,-0.101667f,
            0.600000f,-0.065000f,-0.101667f,
            0.400000f,1.035000f,0.198333f,
            0.600000f,0.735000f,0.598333f,
            0.500000f,0.435000f,0.298333f,
            0.000000f,-0.065000f,-0.301667f,
            0.300000f,1.335000f,-0.201667f,
            0.600000f,1.035000f,-0.101667f,
            2.000000f,0.135000f,-0.301667f,
            0.600000f,1.035000f,-0.101667f,
            0.600000f,1.035000f,-0.001667f,
            0.600000f,1.035000f,-0.001667f,
            1.400000f,-0.265000f,-0.201667f,
            2.000000f,0.135000f,-0.301667f,
            1.400000f,-0.765000f,-0.301667f,
            1.400000f,-0.265000f,-0.201667f,
            1.500000f,-0.265000f,-0.301667f,
            1.700000f,-0.765000f,-0.301667f,
            1.400000f,-0.265000f,-0.201667f,
            1.400000f,-0.765000f,-0.301667f,
            0.500000f,-0.165000f,0.298333f,
            0.400000f,0.435000f,0.298333f,
            0.000000f,-0.165000f,0.198333f,
            0.000000f,-0.165000f,0.198333f,
            0.400000f,0.435000f,0.298333f,
            0.000000f,0.435000f,0.198333f,
            0.600000f,1.035000f,-0.001667f,
            0.500000f,0.435000f,0.298333f,
            0.700000f,-0.165000f,-0.001667f,
            0.600000f,1.035000f,-0.101667f,
            2.000000f,0.135000f,-0.301667f,
            1.500000f,-0.265000f,-0.301667f,
            0.000000f,1.135000f,0.098333f,
            0.400000f,0.435000f,0.298333f,
            0.300000f,1.035000f,0.198333f,
            0.500000f,-0.665000f,-0.101667f,
            0.000000f,-0.065000f,-0.301667f,
            0.600000f,-0.065000f,-0.101667f,
            0.700000f,-1.265000f,-0.201667f,
            0.000000f,-0.765000f,-0.301667f,
            0.500000f,-0.665000f,-0.101667f,
            0.000000f,-1.165000f,0.098333f,
            0.700000f,-1.265000f,-0.201667f,
            0.500000f,-0.665000f,-0.101667f,
            0.300000f,1.035000f,0.198333f,
            0.600000f,0.735000f,0.598333f,
            0.600000f,1.035000f,0.598333f,
            0.400000f,1.035000f,0.198333f,
            0.300000f,1.335000f,0.198333f,
            0.300000f,1.035000f,0.198333f,
            0.600000f,0.735000f,0.598333f,
            0.400000f,1.035000f,0.198333f,
            0.600000f,1.035000f,0.598333f,
            1.500000f,-0.265000f,-0.301667f,
            0.700000f,-0.165000f,-0.001667f,
            0.600000f,-0.065000f,-0.101667f,
            1.400000f,-0.265000f,-0.201667f,
            0.600000f,1.035000f,-0.001667f,
            0.700000f,-0.165000f,-0.001667f,
            0.000000f,-1.665000f,-0.301667f,
            0.000000f,-2.664999f,-0.201667f,
            -0.200000f,-2.664999f,-0.201667f,
            0.000000f,-0.065000f,-0.301667f,
            0.000000f,-0.765000f,-0.301667f,
            -0.500000f,-0.665000f,-0.101667f,
            -0.300000f,1.335000f,-0.201667f,
            0.000000f,1.035000f,-0.201667f,
            0.000000f,-0.065000f,-0.301667f,
            0.000000f,-0.765000f,-0.301667f,
            0.000000f,-1.665000f,-0.301667f,
            -0.700000f,-1.265000f,-0.201667f,
            -0.200000f,-1.665000f,-0.001667f,
            0.000000f,-2.365000f,-0.101667f,
            0.000000f,-1.965000f,0.098333f,
            -0.700000f,-0.165000f,-0.001667f,
            -1.400000f,-0.265000f,-0.201667f,
            -1.500000f,-0.265000f,-0.301667f,
            -0.500000f,1.335000f,-0.001667f,
            -0.300000f,1.335000f,-0.201667f,
            -0.600000f,1.035000f,-0.101667f,
            -0.100000f,1.335000f,-0.001667f,
            0.000000f,1.135000f,-0.001667f,
            0.000000f,1.035000f,-0.201667f,
            -0.700000f,-1.265000f,-0.201667f,
            -0.200000f,-1.665000f,-0.001667f,
            0.000000f,-1.165000f,0.098333f,
            -0.200000f,-2.664999f,-0.201667f,
            0.000000f,-2.664999f,-0.201667f,
            0.000000f,-2.365000f,-0.101667f,
            -0.200000f,-2.664999f,-0.201667f,
            0.000000f,-2.365000f,-0.101667f,
            -0.200000f,-1.665000f,-0.001667f,
            -0.500000f,-0.665000f,-0.101667f,
            -0.700000f,-0.165000f,-0.001667f,
            -0.600000f,-0.065000f,-0.101667f,
            -0.200000f,-2.664999f,-0.201667f,
            -0.700000f,-1.265000f,-0.201667f,
            0.000000f,-1.665000f,-0.301667f,
            -1.500000f,-0.265000f,-0.301667f,
            -1.400000f,-0.765000f,-0.301667f,
            -1.700000f,-0.765000f,-0.301667f,
            0.000000f,-1.965000f,0.098333f,
            0.000000f,-1.165000f,0.098333f,
            -0.200000f,-1.665000f,-0.001667f,
            -1.500000f,-0.265000f,-0.301667f,
            -0.600000f,1.035000f,-0.101667f,
            -0.600000f,-0.065000f,-0.101667f,
            -0.200000f,-1.665000f,-0.001667f,
            -0.700000f,-1.265000f,-0.201667f,
            -0.200000f,-2.664999f,-0.201667f,
            -0.500000f,-0.665000f,-0.101667f,
            0.000000f,-1.165000f,0.098333f,
            0.000000f,-0.165000f,0.198333f,
            -0.700000f,-0.165000f,-0.001667f,
            0.000000f,-0.165000f,0.198333f,
            -0.500000f,-0.165000f,0.298333f,
            0.000000f,1.135000f,0.098333f,
            0.000000f,1.135000f,-0.001667f,
            -0.100000f,1.335000f,-0.001667f,
            -0.600000f,1.035000f,-0.001667f,
            -0.400000f,1.035000f,0.198333f,
            -0.500000f,1.335000f,-0.001667f,
            -0.700000f,-0.165000f,-0.001667f,
            -0.500000f,-0.165000f,0.298333f,
            -0.500000f,0.435000f,0.298333f,
            -0.600000f,0.735000f,0.598333f,
            -0.400000f,0.435000f,0.298333f,
            -0.300000f,1.035000f,0.198333f,
            -0.400000f,0.435000f,0.298333f,
            -0.500000f,0.435000f,0.298333f,
            -0.500000f,-0.165000f,0.298333f,
            0.000000f,-0.165000f,0.198333f,
            -0.700000f,-0.165000f,-0.001667f,
            -0.500000f,-0.665000f,-0.101667f,
            -0.400000f,0.435000f,0.298333f,
            0.000000f,0.435000f,0.198333f,
            0.000000f,1.135000f,0.098333f,
            -0.500000f,0.435000f,0.298333f,
            -0.400000f,1.035000f,0.198333f,
            -0.600000f,1.035000f,-0.001667f,
            -0.600000f,1.035000f,0.598333f,
            -0.300000f,1.035000f,0.198333f,
            -0.400000f,1.035000f,0.198333f,
            -0.500000f,0.435000f,0.298333f,
            -0.400000f,0.435000f,0.298333f,
            -0.600000f,0.735000f,0.598333f,
            -0.100000f,1.335000f,-0.001667f,
            -0.300000f,1.335000f,0.198333f,
            0.000000f,1.135000f,0.098333f,
            -1.700000f,-0.765000f,-0.301667f,
            -2.000000f,0.135000f,-0.301667f,
            -1.500000f,-0.265000f,-0.301667f,
            -1.400000f,-0.265000f,-0.201667f,
            -2.000000f,0.135000f,-0.301667f,
            -1.700000f,-0.765000f,-0.301667f,
            -0.600000f,1.035000f,-0.101667f,
            -0.600000f,1.035000f,-0.001667f,
            -0.500000f,1.335000f,-0.001667f,
            -0.300000f,1.335000f,0.198333f,
            -0.300000f,1.035000f,0.198333f,
            0.000000f,1.135000f,0.098333f,
            -0.500000f,1.335000f,-0.001667f,
            -0.300000f,1.335000f,0.198333f,
            -0.100000f,1.335000f,-0.001667f,
            -0.100000f,1.335000f,-0.001667f,
            -0.300000f,1.335000f,-0.201667f,
            -0.500000f,1.335000f,-0.001667f,
            -0.300000f,1.335000f,0.198333f,
            -0.500000f,1.335000f,-0.001667f,
            -0.400000f,1.035000f,0.198333f,
            0.000000f,1.035000f,-0.201667f,
            -0.300000f,1.335000f,-0.201667f,
            -0.100000f,1.335000f,-0.001667f,
            0.000000f,-0.065000f,-0.301667f,
            -0.600000f,-0.065000f,-0.101667f,
            -0.600000f,1.035000f,-0.101667f,
            -0.400000f,1.035000f,0.198333f,
            -0.500000f,0.435000f,0.298333f,
            -0.600000f,0.735000f,0.598333f,
            0.000000f,-0.065000f,-0.301667f,
            -0.600000f,1.035000f,-0.101667f,
            -0.300000f,1.335000f,-0.201667f,
            -2.000000f,0.135000f,-0.301667f,
            -0.600000f,1.035000f,-0.001667f,
            -0.600000f,1.035000f,-0.101667f,
            -0.600000f,1.035000f,-0.001667f,
            -2.000000f,0.135000f,-0.301667f,
            -1.400000f,-0.265000f,-0.201667f,
            -1.400000f,-0.765000f,-0.301667f,
            -1.500000f,-0.265000f,-0.301667f,
            -1.400000f,-0.265000f,-0.201667f,
            -1.700000f,-0.765000f,-0.301667f,
            -1.400000f,-0.765000f,-0.301667f,
            -1.400000f,-0.265000f,-0.201667f,
            -0.500000f,-0.165000f,0.298333f,
            0.000000f,-0.165000f,0.198333f,
            -0.400000f,0.435000f,0.298333f,
            0.000000f,-0.165000f,0.198333f,
            0.000000f,0.435000f,0.198333f,
            -0.400000f,0.435000f,0.298333f,
            -0.600000f,1.035000f,-0.001667f,
            -0.700000f,-0.165000f,-0.001667f,
            -0.500000f,0.435000f,0.298333f,
            -0.600000f,1.035000f,-0.101667f,
            -1.500000f,-0.265000f,-0.301667f,
            -2.000000f,0.135000f,-0.301667f,
            0.000000f,1.135000f,0.098333f,
            -0.300000f,1.035000f,0.198333f,
            -0.400000f,0.435000f,0.298333f,
            -0.500000f,-0.665000f,-0.101667f,
            -0.600000f,-0.065000f,-0.101667f,
            0.000000f,-0.065000f,-0.301667f,
            -0.700000f,-1.265000f,-0.201667f,
            -0.500000f,-0.665000f,-0.101667f,
            0.000000f,-0.765000f,-0.301667f,
            0.000000f,-1.165000f,0.098333f,
            -0.500000f,-0.665000f,-0.101667f,
            -0.700000f,-1.265000f,-0.201667f,
            -0.300000f,1.035000f,0.198333f,
            -0.600000f,1.035000f,0.598333f,
            -0.600000f,0.735000f,0.598333f,
            -0.400000f,1.035000f,0.198333f,
            -0.300000f,1.035000f,0.198333f,
            -0.300000f,1.335000f,0.198333f,
            -0.600000f,0.735000f,0.598333f,
            -0.600000f,1.035000f,0.598333f,
            -0.400000f,1.035000f,0.198333f,
            -1.500000f,-0.265000f,-0.301667f,
            -0.600000f,-0.065000f,-0.101667f,
            -0.700000f,-0.165000f,-0.001667f,
            -1.400000f,-0.265000f,-0.201667f,
            -0.700000f,-0.165000f,-0.001667f,
            -0.600000f,1.035000f,-0.001667f
    };
    private static final float uvs[]={
            0.375015f,0.257812f,0.320328f,0.000000f,0.375015f,0.000000f,
            0.375015f,0.625000f,0.250015f,0.507812f,0.375015f,0.476562f,
            0.296890f,1.000000f,0.375015f,0.625000f,0.375015f,0.921875f,
            0.375015f,0.476562f,0.187515f,0.351562f,0.375015f,0.257812f,
            0.054703f,0.250000f,0.000015f,0.171875f,0.000015f,0.070312f,
            1.000015f,0.718750f,0.804703f,0.695312f,0.835953f,0.687500f,
            0.187515f,1.000000f,0.187515f,0.921875f,0.296890f,1.000000f,
            0.375015f,1.000000f,0.375015f,0.921875f,0.367203f,0.945312f,
            0.187515f,0.351562f,0.000015f,0.375000f,0.054703f,0.250000f,
            0.054703f,0.000000f,0.000015f,0.070312f,0.000015f,0.000000f,
            0.054703f,0.000000f,0.054703f,0.250000f,0.000015f,0.070312f,
            0.250015f,0.507812f,0.187515f,0.625000f,0.187515f,0.625000f,
            0.320328f,0.000000f,0.375015f,0.257812f,0.187515f,0.351562f,
            0.804703f,0.695312f,0.765640f,0.562500f,0.835953f,0.562500f,
            0.000015f,0.171875f,0.054703f,0.250000f,0.000015f,0.375000f,
            0.804703f,0.695312f,1.000015f,0.718750f,1.000015f,1.000000f,
            0.054703f,0.250000f,0.054703f,0.000000f,0.187515f,0.351562f,
            0.125015f,0.500000f,0.000015f,0.625000f,0.000015f,0.375000f,
            0.562515f,0.312500f,0.500015f,0.375000f,0.375015f,0.312500f,
            0.000015f,0.921875f,0.000015f,1.000000f,0.000015f,0.945312f,
            0.187515f,0.921875f,0.187515f,1.000000f,0.101578f,0.921875f,
            0.187515f,0.625000f,0.125015f,0.781250f,0.125015f,0.625000f,
            0.468765f,0.492188f,0.375015f,0.562500f,0.375015f,0.398438f,
            0.101578f,0.781250f,0.125015f,0.625000f,0.125015f,0.781250f,
            0.000015f,0.625000f,0.125015f,0.500000f,0.187515f,0.625000f,
            0.101578f,0.781250f,0.000015f,0.921875f,0.000015f,0.781250f,
            0.125015f,0.781250f,0.187515f,0.921875f,0.101578f,0.921875f,
            0.468765f,0.562500f,0.375015f,0.554688f,0.375015f,0.562500f,
            0.375015f,0.398438f,0.468765f,0.492188f,0.375015f,0.398438f,
            0.000015f,1.000000f,0.000015f,0.921875f,0.078140f,1.000000f,
            0.765640f,0.562500f,0.804703f,0.695312f,0.695328f,0.796875f,
            0.539078f,0.687500f,0.609390f,0.562500f,0.679703f,0.796875f,
            0.187515f,0.921875f,0.187515f,1.000000f,0.187515f,0.921875f,
            0.078140f,1.000000f,0.000015f,0.921875f,0.078140f,0.921875f,
            0.562515f,0.500000f,0.687515f,0.500000f,0.625015f,0.562500f,
            0.687515f,0.500000f,0.562515f,0.500000f,0.625015f,0.437500f,
            0.078140f,1.000000f,0.101578f,0.921875f,0.187515f,1.000000f,
            0.375015f,0.921875f,0.375015f,1.000000f,0.296890f,1.000000f,
            0.375015f,0.625000f,0.187515f,0.921875f,0.187515f,0.625000f,
            0.562515f,0.562500f,0.468765f,0.492188f,0.562515f,0.398438f,
            0.375015f,0.625000f,0.296890f,1.000000f,0.187515f,0.921875f,
            0.695328f,0.796875f,1.000015f,1.000000f,1.000015f,1.000000f,
            0.375015f,1.000000f,0.539078f,0.687500f,0.679703f,0.796875f,
            0.835953f,0.562500f,0.835953f,0.687500f,0.804703f,0.695312f,
            0.609390f,0.562500f,0.539078f,0.687500f,0.539078f,0.562500f,
            0.125015f,0.625000f,0.101578f,0.781250f,0.000015f,0.625000f,
            0.000015f,0.625000f,0.101578f,0.781250f,0.000015f,0.781250f,
            0.187515f,0.921875f,0.125015f,0.781250f,0.187515f,0.625000f,
            1.000015f,1.000000f,0.695328f,0.796875f,0.804703f,0.695312f,
            0.000015f,0.921875f,0.101578f,0.781250f,0.078140f,0.921875f,
            0.250015f,0.507812f,0.375015f,0.625000f,0.187515f,0.625000f,
            0.187515f,0.351562f,0.375015f,0.476562f,0.250015f,0.507812f,
            0.000015f,0.375000f,0.187515f,0.351562f,0.125015f,0.500000f,
            0.375015f,0.562500f,0.468765f,0.492188f,0.468765f,0.562500f,
            0.101578f,0.921875f,0.078140f,1.000000f,0.078140f,0.921875f,
            0.468765f,0.492188f,0.562515f,0.562500f,0.468765f,0.562500f,
            0.804703f,0.695312f,1.000015f,0.718750f,1.000015f,0.718750f,
            0.539078f,0.687500f,0.375015f,1.000000f,0.375015f,0.718750f,
            0.375015f,0.257812f,0.375015f,0.000000f,0.320328f,0.000000f,
            0.375015f,0.625000f,0.375015f,0.476562f,0.250015f,0.507812f,
            0.296890f,1.000000f,0.375015f,0.921875f,0.375015f,0.625000f,
            0.375015f,0.476562f,0.375015f,0.257812f,0.187515f,0.351562f,
            0.054703f,0.250000f,0.000015f,0.070312f,0.000015f,0.171875f,
            1.000015f,0.718750f,0.835953f,0.687500f,0.804703f,0.695312f,
            0.187515f,1.000000f,0.296890f,1.000000f,0.187515f,0.921875f,
            0.375015f,1.000000f,0.367203f,0.945312f,0.375015f,0.921875f,
            0.187515f,0.351562f,0.054703f,0.250000f,0.000015f,0.375000f,
            0.054703f,0.000000f,0.000015f,0.000000f,0.000015f,0.070312f,
            0.054703f,0.000000f,0.000015f,0.070312f,0.054703f,0.250000f,
            0.250015f,0.507812f,0.187515f,0.625000f,0.187515f,0.625000f,
            0.320328f,0.000000f,0.187515f,0.351562f,0.375015f,0.257812f,
            0.804703f,0.695312f,0.835953f,0.562500f,0.765640f,0.562500f,
            0.000015f,0.171875f,0.000015f,0.375000f,0.054703f,0.250000f,
            0.804703f,0.695312f,1.000015f,1.000000f,1.000015f,0.718750f,
            0.054703f,0.250000f,0.187515f,0.351562f,0.054703f,0.000000f,
            0.125015f,0.500000f,0.000015f,0.375000f,0.000015f,0.625000f,
            0.562515f,0.312500f,0.375015f,0.312500f,0.500015f,0.375000f,
            0.000015f,0.921875f,0.000015f,0.945312f,0.000015f,1.000000f,
            0.187515f,0.921875f,0.101578f,0.921875f,0.187515f,1.000000f,
            0.187515f,0.625000f,0.125015f,0.625000f,0.125015f,0.781250f,
            0.468765f,0.492188f,0.375015f,0.398438f,0.375015f,0.562500f,
            0.101578f,0.781250f,0.125015f,0.781250f,0.125015f,0.625000f,
            0.000015f,0.625000f,0.187515f,0.625000f,0.125015f,0.500000f,
            0.101578f,0.781250f,0.000015f,0.781250f,0.000015f,0.921875f,
            0.125015f,0.781250f,0.101578f,0.921875f,0.187515f,0.921875f,
            0.468765f,0.562500f,0.375015f,0.562500f,0.375015f,0.554688f,
            0.375015f,0.398438f,0.375015f,0.398438f,0.468765f,0.492188f,
            0.000015f,1.000000f,0.078140f,1.000000f,0.000015f,0.921875f,
            0.765640f,0.562500f,0.695328f,0.796875f,0.804703f,0.695312f,
            0.539078f,0.687500f,0.679703f,0.796875f,0.609390f,0.562500f,
            0.187515f,0.921875f,0.187515f,0.921875f,0.187515f,1.000000f,
            0.078140f,1.000000f,0.078140f,0.921875f,0.000015f,0.921875f,
            0.562515f,0.500000f,0.625015f,0.562500f,0.687515f,0.500000f,
            0.687515f,0.500000f,0.625015f,0.437500f,0.562515f,0.500000f,
            0.078140f,1.000000f,0.187515f,1.000000f,0.101578f,0.921875f,
            0.375015f,0.921875f,0.296890f,1.000000f,0.375015f,1.000000f,
            0.375015f,0.625000f,0.187515f,0.625000f,0.187515f,0.921875f,
            0.562515f,0.562500f,0.562515f,0.398438f,0.468765f,0.492188f,
            0.375015f,0.625000f,0.187515f,0.921875f,0.296890f,1.000000f,
            0.695328f,0.796875f,1.000015f,1.000000f,1.000015f,1.000000f,
            0.375015f,1.000000f,0.679703f,0.796875f,0.539078f,0.687500f,
            0.835953f,0.562500f,0.804703f,0.695312f,0.835953f,0.687500f,
            0.609390f,0.562500f,0.539078f,0.562500f,0.539078f,0.687500f,
            0.125015f,0.625000f,0.000015f,0.625000f,0.101578f,0.781250f,
            0.000015f,0.625000f,0.000015f,0.781250f,0.101578f,0.781250f,
            0.187515f,0.921875f,0.187515f,0.625000f,0.125015f,0.781250f,
            1.000015f,1.000000f,0.804703f,0.695312f,0.695328f,0.796875f,
            0.000015f,0.921875f,0.078140f,0.921875f,0.101578f,0.781250f,
            0.250015f,0.507812f,0.187515f,0.625000f,0.375015f,0.625000f,
            0.187515f,0.351562f,0.250015f,0.507812f,0.375015f,0.476562f,
            0.000015f,0.375000f,0.125015f,0.500000f,0.187515f,0.351562f,
            0.375015f,0.562500f,0.468765f,0.562500f,0.468765f,0.492188f,
            0.101578f,0.921875f,0.078140f,0.921875f,0.078140f,1.000000f,
            0.468765f,0.492188f,0.468765f,0.562500f,0.562515f,0.562500f,
            0.804703f,0.695312f,1.000015f,0.718750f,1.000015f,0.718750f,
            0.539078f,0.687500f,0.375015f,0.718750f,0.375015f,1.000000f
    };
    private static final float normals[]={
            0.000000f,-0.099504f,-0.995037f,
            0.371391f,0.000000f,-0.928477f,
            -0.090167f,0.090167f,-0.991837f,
            0.141421f,0.000000f,-0.989950f,
            0.745356f,-0.298143f,0.596285f,
            -0.192450f,-0.962250f,-0.192450f,
            0.639602f,0.426402f,-0.639602f,
            -0.872871f,0.436436f,-0.218218f,
            0.390398f,-0.027885f,0.920224f,
            0.000000f,-0.316228f,0.948683f,
            0.192450f,-0.192450f,0.962250f,
            0.646997f,-0.107833f,-0.754829f,
            0.176313f,-0.062969f,-0.982318f,
            0.000000f,0.000000f,-1.000000f,
            0.447213f,0.000000f,0.894427f,
            -0.216930f,-0.000000f,-0.976187f,
            0.481330f,-0.171904f,0.859518f,
            0.445435f,-0.089087f,0.890871f,
            0.000000f,-1.000000f,0.000000f,
            -0.894427f,0.447214f,0.000000f,
            0.688247f,0.229416f,0.688247f,
            0.832050f,-0.000000f,0.554700f,
            -0.813123f,-0.038720f,0.580802f,
            0.000000f,0.000000f,1.000000f,
            0.262976f,-0.289274f,0.920415f,
            -0.240240f,0.137280f,0.960957f,
            0.688247f,0.229416f,0.688247f,
            0.000000f,1.000000f,0.000000f,
            0.000000f,-0.707107f,0.707107f,
            -0.577350f,0.577350f,0.577350f,
            0.000000f,-0.000000f,-1.000000f,
            0.209020f,-0.069673f,0.975426f,
            0.948683f,0.316228f,0.000000f,
            -0.316228f,0.000000f,0.948683f,
            -0.000000f,1.000000f,0.000000f,
            0.000000f,1.000000f,-0.000000f,
            0.688247f,0.229416f,0.688247f,
            -0.577350f,0.577351f,-0.577350f,
            0.316228f,0.000000f,-0.948683f,
            0.915643f,0.087204f,-0.392419f,
            0.316228f,-0.000000f,-0.948683f,
            0.540758f,0.841179f,0.000000f,
            0.187403f,-0.035696f,0.981634f,
            -0.700140f,0.140028f,-0.700140f,
            0.000000f,-0.196116f,0.980581f,
            -0.196012f,-0.032669f,0.980057f,
            -0.242536f,0.000000f,0.970142f,
            0.891953f,0.074329f,0.445976f,
            -0.078579f,0.098223f,-0.992057f,
            -0.280386f,0.112155f,0.953312f,
            0.315789f,-0.052632f,-0.947368f,
            0.314157f,0.257038f,-0.913913f,
            0.391037f,-0.023002f,0.920087f,
            -0.800000f,0.000000f,0.600000f,
            -0.000000f,0.000000f,1.000000f,
            0.894427f,0.000000f,-0.447214f,
            -0.293294f,-0.806559f,-0.513265f,
            0.277703f,0.023142f,0.960388f,
            0.000000f,-0.099504f,-0.995037f,
            -0.371391f,0.000000f,-0.928477f,
            0.090167f,0.090167f,-0.991837f,
            -0.141421f,0.000000f,-0.989950f,
            -0.745356f,-0.298143f,0.596285f,
            0.192450f,-0.962250f,-0.192450f,
            -0.639602f,0.426402f,-0.639602f,
            0.872871f,0.436436f,-0.218218f,
            -0.390398f,-0.027885f,0.920224f,
            0.000000f,-0.316228f,0.948683f,
            -0.192450f,-0.192450f,0.962250f,
            -0.646997f,-0.107833f,-0.754829f,
            -0.176313f,-0.062969f,-0.982318f,
            0.000000f,0.000000f,-1.000000f,
            -0.447213f,0.000000f,0.894427f,
            0.216930f,0.000000f,-0.976187f,
            -0.481330f,-0.171904f,0.859518f,
            -0.445435f,-0.089087f,0.890871f,
            0.000000f,-1.000000f,-0.000000f,
            0.894427f,0.447214f,-0.000000f,
            -0.688247f,0.229416f,0.688247f,
            -0.832050f,0.000000f,0.554700f,
            0.813123f,-0.038720f,0.580802f,
            0.000000f,0.000000f,1.000000f,
            -0.262976f,-0.289274f,0.920415f,
            0.240240f,0.137280f,0.960957f,
            -0.688247f,0.229416f,0.688247f,
            0.000000f,1.000000f,-0.000000f,
            0.000000f,-0.707107f,0.707107f,
            0.577350f,0.577350f,0.577350f,
            -0.000000f,-0.000000f,-1.000000f,
            -0.209020f,-0.069673f,0.975426f,
            -0.948683f,0.316228f,0.000000f,
            0.316228f,-0.000000f,0.948683f,
            0.000000f,1.000000f,0.000000f,
            -0.000000f,1.000000f,0.000000f,
            -0.688247f,0.229416f,0.688247f,
            0.577350f,0.577351f,-0.577350f,
            -0.316228f,-0.000000f,-0.948683f,
            -0.915643f,0.087204f,-0.392419f,
            -0.316228f,-0.000000f,-0.948683f,
            -0.540758f,0.841179f,0.000000f,
            -0.187403f,-0.035696f,0.981634f,
            0.700140f,0.140028f,-0.700140f,
            0.000000f,-0.196116f,0.980581f,
            0.196012f,-0.032669f,0.980057f,
            0.242536f,0.000000f,0.970142f,
            -0.891953f,0.074329f,0.445976f,
            0.078579f,0.098223f,-0.992057f,
            0.280386f,0.112155f,0.953312f,
            -0.315789f,-0.052632f,-0.947368f,
            -0.314157f,0.257038f,-0.913913f,
            -0.391037f,-0.023002f,0.920087f,
            0.800000f,-0.000000f,0.600000f,
            0.000000f,0.000000f,1.000000f,
            -0.894427f,-0.000000f,-0.447214f,
            0.293294f,-0.806559f,-0.513265f,
            -0.277703f,0.023142f,0.960388f
    };
    @Override
    float[] getVertices(){return Fighter.vertices;}


    @Override
    float[] getUvs(){return Fighter.uvs;}

    @Override
    short[] getDrawOrder(){return Fighter.drawOrder;}

    @Override
    float[] getNormals(){return Fighter.normals;}
}

