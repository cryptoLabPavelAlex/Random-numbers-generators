package com.pavel.alex.lab.first.generator;


public interface Generator {

    long generateNext();

    Type getType();

   default String getName(){
       return this.getClass().getSimpleName();
   }

    enum Type{
        BYTE,BIT
    }


    class LFSR implements Generator {
        private final long characteristicPolynon;

        private final long firstState;

        //defines current state of register
        private long currentState;

        //defines a function for generating elements
        private final long multiplicativeMask;

        //defines a length of register;
        private final int registerLength;

        public LFSR(long characteristicPolynon,long firstState){
            this.characteristicPolynon=characteristicPolynon;
            int n = Long.numberOfLeadingZeros(this.characteristicPolynon) + 1;
            long identityMask = (0xFFFF_FFFF >>> n);
            this.registerLength = 64 - n ;
            this.multiplicativeMask = characteristicPolynon & identityMask;
            this.firstState= firstState & identityMask;
            this.currentState = this.firstState;
        }

        public void reset(){
            currentState = firstState;
        }
        @Override
        public long generateNext(){
            long outputBit = currentState & 1;
            long nextBit = Long.bitCount(currentState & multiplicativeMask) & 1 ;
            currentState = ( currentState >>> 1 ) | (nextBit << (registerLength - 1));
            return outputBit;
        }

        @Override
        public Type getType() {
            return Type.BIT;
        }

        @Override
        public String getName() {
            return "LFSR";
        }

    }

}
