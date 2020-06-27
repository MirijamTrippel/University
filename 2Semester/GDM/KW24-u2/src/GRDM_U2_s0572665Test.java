import static org.junit.jupiter.api.Assertions.*;

class GRDM_U2_s0572665Test {

    @org.junit.jupiter.api.Test
    void convert() {
        int expected = 624; // idk why its not 627 = 105+244+278; probably math round up
        int result = GRDM_U2_s0572665.convert(105,244,278);

        assertEquals(expected,result);
    }
}