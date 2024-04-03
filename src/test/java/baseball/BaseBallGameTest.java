package baseball;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseBallGameTest {


    @Test
    public void 전부_스트라이크_시_출력_테스트() {

        // given
        GameController gameController = new GameController();

        // when
        GameResult gameResult = gameController.verifyNum("308", "308");

        // then
        assertEquals(3, gameResult.getStrikeCount());
        assertEquals(0, gameResult.getBallCount());
    }

    @Test
    public void 세자리_이외입력_예외테스트(){
        //given
        GameController gameController = new GameController();

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            gameController.validateUserNum("12");
        });
    }

}
