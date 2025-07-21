import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class LinesWrapTest {
  private final Test test = new Test();

  public void testAll() throws Exception {
    String testName = "LinesWrapTest.testAll";
    System.out.println(testName + " started.");

    testOneLineWithIdNTag();
    testLinesInListsWMixedIds();

    System.out.println(testName + " done.");
  }

  public void testOneLineWithIdNTag() throws Exception {
    String line = "id is ${id_autoinc_1} for ${code}";
    List<String> lineList = new ArrayList<>();
    lineList.add(line);
    List<String> expected = new ArrayList<>();
    expected.add("id is " + new IdGetStub().next() + " for CovCode");

    Map<String, String> tagMap = new HashMap<>();
    tagMap.put("code", "CovCode");
    List<IdGet> idGets = new ArrayList<>();
    idGets.add(new IdGetStub());
    Line lineWrap = new Line(idGets, new Replace(), tagMap);
    LinesWrap lns = new LinesWrap(lineWrap, lineList);
    List<String> actual = lns.remake();

    test.show("One line (" + line + ")", listsEqual(actual, expected));
  }

  public void testLinesInListsWMixedIds() throws Exception {
    String covCode = "CovCode";
    Map<String, String> tagMap = new HashMap<>();
    tagMap.put("code", covCode);
    IdGet idGetForExpected = new IdGetStub();
    String expectedId1 = idGetForExpected.next();
    String expectedId2 = idGetForExpected.next();
    String expectedId3 = idGetForExpected.next();

    List<IdGet> idGets = new ArrayList<>();
    idGets.add(new IdGetStub());
    Line theOnlyLineWrap = new Line(idGets, new Replace(), tagMap);

    List<String> lineList1 = new ArrayList<>();
    List<String> expected1 = new ArrayList<>();
    lineList1.add("id is ${id_autoinc_1} for ${code}");
    expected1.add("id is " + expectedId1 + " for " + covCode);
    lineList1.add("and another id ${id_autoinc_2} for the same code");
    expected1.add("and another id " + expectedId2 + " for the same code");

    LinesWrap lns1 = new LinesWrap(theOnlyLineWrap, lineList1);
    List<String> actual1 = lns1.remake();

    test.show("Lines in list 1 with mixed ids", listsEqual(actual1, expected1));

    List<String> lineList2 = new ArrayList<>();
    List<String> expected2 = new ArrayList<>();
    lineList2.add("Id3: ${id_autoinc_3}, code is ${code}.");
    expected2.add("Id3: " + expectedId3 + ", code is " + covCode + ".");
    lineList2.add("");
    expected2.add("");
    lineList2.add("Changed order of ids. Id2: ${id_autoinc_2}, code is ${code},");
    expected2.add("Changed order of ids. Id2: " + expectedId2 + ", code is " + covCode + ",");
    lineList2.add("Id1: ${id_autoinc_1}.");
    expected2.add("Id1: " + expectedId1 + ".");

    LinesWrap lns2 = new LinesWrap(theOnlyLineWrap, lineList2);
    List<String> actual2 = lns2.remake();

    test.show("Lines in list 2 with mixed ids", listsEqual(actual2, expected2));
  }

  private boolean listsEqual(List<String> actualList, List<String> expectedList) {
    int size = actualList.size();
    if (size != expectedList.size()) {
      System.out.println("actual list size is " + size +
        ", expected list size is " + expectedList.size());
      return false;
    }

    boolean same = true;

    for (int i = 0; i < size; i++) {
      if (!actualList.get(i).equals(expectedList.get(i))) {
        System.out.println("item " + i +
          ": in actual list: " + actualList.get(i) +
          "; in expected list: " + expectedList.get(i));
        same = false;
        break;
      }
    }

    return same;
  }
}