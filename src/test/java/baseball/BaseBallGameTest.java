package baseball;



import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseBallGameTest {



    @Test
    public void 전부_스트라이크_시_출력_테스트() {

    // given
        StartGame startGame = new StartGame();

    // when
        GameResult gameResult = startGame.verifyNum("308", "308");

    // then
        assertEquals(3, equals(gameResult.getStrikeCount()));
        assertEquals(0, equals(gameResult.getBallCount()));
    }

}
