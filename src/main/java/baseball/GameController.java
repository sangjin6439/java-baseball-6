package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private static final int DIGITS_LENGTH = 3;

    public void startGame() {
        boolean continueGame = true;
        while (continueGame) {
            String computerNum = createComputerNum();
            boolean gameResult = playGame(computerNum);
            if (!gameResult) {
                break;
            }
        }
    }

    //게임 초기 설정(컴퓨터가 정한 랜덤값)
    public String createComputerNum() {
        StringBuilder sb = new StringBuilder();
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < DIGITS_LENGTH) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }

        for (int num : computer) {
            sb.append(num);
        }

        return sb.toString();
    }

    public boolean playGame(String computerNum) {

        System.out.println("숫자 야구 게임을 시작합니다.");

        while (true) {
            System.out.print("숫자를 입력해주세요 : ");

            //유저 입력
            String userNum = Console.readLine();

            validateUserNum(userNum);

            GameResult gameResult = verifyNum(userNum, computerNum);
            boolean gameContinue = printGameResult(gameResult.getStrikeCount(), gameResult.getBallCount());

            // 게임 결과에 따라 반복문 탈출
            if (!gameContinue) {
                return false;
            }
        }
    }

    //유저의 값과 컴퓨터 값을 비교
    public GameResult verifyNum(String userNum, String computerNum) {
        int strikeCount = 0;
        int ballCount = 0;
        for (int i = 0; i < 3; i++) {
            if (userNum.charAt(i) == computerNum.charAt(i)) {
                strikeCount++;
            } else if (computerNum.contains(String.valueOf(userNum.charAt(i)))) {
                ballCount++;
            }
        }
        return new GameResult(strikeCount, ballCount);
    }

    public boolean printGameResult(int strikeCount, int ballCount) {
        //모두 맞출 시 1 또는 2 입력, 1 입력 시 게임 다시 진행, 2 입력 시 게임 종료
        if (strikeCount == 3) {
            System.out.println(strikeCount + "스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            return askContinueGame();
        }

        if (ballCount > 0 && strikeCount == 0) {
            System.out.println(ballCount + "볼 ");
        } else if (ballCount > 0 && strikeCount != 0) {
            System.out.print(ballCount + "볼 ");
        }

        if (strikeCount > 0 && strikeCount < 3) {
            System.out.println(strikeCount + "스트라이크");
        }

        if (strikeCount == 0 && ballCount == 0) {
            System.out.println("낫싱");
        }
        return true;
    }

    public boolean askContinueGame() {
        int userChoose = Integer.parseInt(Console.readLine());
        if (userChoose == 1) {
            startGame();
        }
        return false;
    }

    //길이가 3이 아니면 IllegalArgumentException 발생 시키고 프로그램 종료
    public void validateUserNum(String userNum) {
        if (userNum.length() != DIGITS_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}