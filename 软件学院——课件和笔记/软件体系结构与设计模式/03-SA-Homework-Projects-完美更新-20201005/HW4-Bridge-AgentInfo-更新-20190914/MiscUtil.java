import java.io.*;
import java.util.*;


public class MiscUtil {


  public static boolean hasDuplicates(Vector v) {
    int i = 0;
    int j = 0;
    boolean duplicates = false;

    for (i = 0; i < v.size() - 1; i++) {
      for (j = (i + 1); j < v.size(); j++) {
        if (v.elementAt(i).toString().equalsIgnoreCase(
              v.elementAt(j).toString())) {
          duplicates = true;
        }

      }

    }

    return duplicates;
  }

  public static Vector removeDuplicates(Vector s) {
    int i = 0;
    int j = 0;
    boolean duplicates = false;

    Vector v = new Vector();

    for (i = 0; i < s.size(); i++) {
      duplicates = false;
      for (j = (i + 1); j < s.size(); j++) {
        if (s.elementAt(i).toString().equalsIgnoreCase(
              s.elementAt(j).toString())) {
          duplicates = true;
        }

      }
      if (duplicates == false) {
        v.addElement(s.elementAt(i).toString().trim());
      }

    }

    return v;
  }

  public static Vector removeDuplicateDomains(Vector s) {
    int i = 0;
    int j = 0;
    boolean duplicates = false;
    String str1 = "";
    String str2 = "";

    Vector v = new Vector();

    for (i = 0; i < s.size(); i++) {
      duplicates = false;
      for (j = (i + 1); j < s.size(); j++) {
        str1 = "";
        str2 = "";
        str1 = s.elementAt(i).toString().trim();
        str2 = s.elementAt(j).toString().trim();
        if (str1.indexOf('@') > -1) {
          str1 = str1.substring(str1.indexOf('@'));
        }
        if (str2.indexOf('@') > -1) {
          str2 = str2.substring(str2.indexOf('@'));
        }

        if (str1.equalsIgnoreCase(str2)) {
          duplicates = true;
        }

      }
      if (duplicates == false) {
        v.addElement(s.elementAt(i).toString().trim());
      }

    }

    return v;
  }

  public static boolean areVectorsEqual(Vector a, Vector b) {
    if (a.size() != b.size()) {
      return false;
    }

    int i = 0;
    int vectorSize = a.size();
    boolean identical = true;

    for (i = 0; i < vectorSize; i++) {
      if (!(a.elementAt(i).toString().equalsIgnoreCase(
              b.elementAt(i).toString()))) {
        identical = false;
      }
    }

    return identical;
  }

  public static Vector removeDuplicates(Vector a, Vector b) {

    int i = 0;
    int j = 0;
    boolean present = true;
    Vector v = new Vector();


    for (i = 0; i < a.size(); i++) {
      present = false;
      for (j = 0; j < b.size(); j++) {
        if (a.elementAt(i).toString().equalsIgnoreCase(
              b.elementAt(j).toString())) {
          present = true;
        }
      }
      if (!(present)) {
        v.addElement(a.elementAt(i));
      }
    }

    return v;
  }


}// end of class

