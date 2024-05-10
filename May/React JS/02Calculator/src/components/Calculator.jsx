import React, { useState, useCallback, useEffect } from "react";
import BackspaceIcon from "@mui/icons-material/Backspace";

function Calculator() {
  const [previousCalText, setPreviousCalText] = useState("");
  const [currentCalcText, setCurrentCalcText] = useState("");
  const [currOperation, setCurrOperation] = useState("");
  const [prevOperation, setPrevOperation] = useState("");
  const [operator, setOperator] = useState(undefined);

  //for reset the calc
  const clear = useCallback(() => {
    setCurrOperation("");
    setPrevOperation("");
    setOperator(undefined);
    updateOutput();
  }, []);

  //remove one digit from an end
  const backspace = useCallback(() => {
    setCurrOperation((prevCurrOperation) =>
      prevCurrOperation.toString().slice(0, -1)
    );
    updateOutput();
  }, []);

  //calculate the values
  const calculate = useCallback(() => {
    let val;
    const prevVal = parseFloat(prevOperation);
    const currVal = parseFloat(currOperation);
    if (isNaN(prevVal) || isNaN(currVal)) return;
    switch (operator) {
      case "+":
        val = prevVal + currVal;
        break;
      case "-":
        val = prevVal - currVal;
        break;
      case "*":
        val = prevVal * currVal;
        break;
      case "/":
        val = prevVal / currVal;
        break;
      case "%":
        val = prevVal % currVal;
        break;
      default:
        return;
    }
    setCurrOperation(val);
    setOperator(undefined);
    setPrevOperation("");
  }, [currOperation, operator, prevOperation]);

  //add number in the display
  const addNum = useCallback(
    (number) => {
      if (number === "." && currOperation.includes(".")) return;
      setCurrOperation(
        (prevCurrOperation) => prevCurrOperation.toString() + number.toString()
      );
      updateOutput();
    },
    [currOperation]
  );

  //operator selection
  const selectOperator = useCallback(
    (selectedOperator) => {
      if (currOperation === "") return;
      if (prevOperation !== "") {
        calculate();
      }
      setOperator(selectedOperator);
      if (currOperation === "" && prevOperation !== "") {
        return;
      } else {
        setPrevOperation(currOperation);
        setCurrOperation("");
      }
      updateOutput();
    },
    [calculate, currOperation, prevOperation]
  );

  //format number in display
  const formatNumber = useCallback((number) => {
    const stringNumber = number.toString();
    const integerDigits = parseFloat(stringNumber.split(".")[0]);
    console.log(integerDigits);

    const decimalDigits = stringNumber.split(".")[1];
    console.log(decimalDigits);
    let integerDisplay;
    if (isNaN(integerDigits)) {
      integerDisplay = "";
    } else {
      integerDisplay = integerDigits.toLocaleString("en-us", {
        maximumFractionDigits: 0,
      });
    }
    if (decimalDigits != null) {
      return `${integerDisplay}.${decimalDigits}`;
    } else {
      return integerDisplay;
    }
  }, []);

  const equalFunction = useCallback(() => {
    if (currOperation === "" && prevOperation !== "") {
      setCurrOperation(prevOperation);
      setPrevOperation("");
      setOperator(undefined);
    } else {
      calculate();
    }
    updateOutput();
  }, [calculate, currOperation, prevOperation]);

  const updateOutput = useCallback(() => {
    setCurrentCalcText(formatNumber(currOperation));
    if (operator != null) {
      setPreviousCalText(
        `${formatNumber(prevOperation)} ${operator.toString()}`
      );
    } else {
      setPreviousCalText("");
    }
  }, [currOperation, formatNumber, operator, prevOperation]);

  useEffect(() => {
    updateOutput();
  }, [updateOutput]);

  return (
    <div className="container">
      <div className="small-container display">
        <div id="calculation">{previousCalText}</div>
        <div id="output">{currentCalcText}</div>
      </div>
      <div className="small-container numpad">
        <div className="row">
          <button className="operator row-1" onClick={clear}>
            AC
          </button>
          <button className="operator row-1" onClick={backspace}>
            <BackspaceIcon />
          </button>
          <button
            className="operator row-1"
            onClick={() => selectOperator("%")}
          >
            %
          </button>
          <button
            className="operator devide"
            onClick={() => selectOperator("/")}
          >
            /
          </button>
        </div>
        <div className="row">
          <button className="number" onClick={() => addNum("7")}>
            7
          </button>
          <button className="number" onClick={() => addNum("8")}>
            8
          </button>
          <button className="number" onClick={() => addNum("9")}>
            9
          </button>
          <button className="operator mul" onClick={() => selectOperator("*")}>
            ×
          </button>
        </div>
        <div className="row">
          <button className="number" onClick={() => addNum("4")}>
            4
          </button>
          <button className="number" onClick={() => addNum("5")}>
            5
          </button>
          <button className="number" onClick={() => addNum("6")}>
            6
          </button>
          <button
            className="operator minus"
            onClick={() => selectOperator("-")}
          >
            −
          </button>
        </div>
        <div className="row">
          <button className="number" onClick={() => addNum("1")}>
            1
          </button>
          <button className="number" onClick={() => addNum("2")}>
            2
          </button>
          <button className="number" onClick={() => addNum("3")}>
            3
          </button>
          <button className="operator plus" onClick={() => selectOperator("+")}>
            +
          </button>
        </div>
        <div className="row">
          <button className="operator"></button>
          <button className="number" onClick={() => addNum("0")}>
            0
          </button>
          <button className="number float" onClick={() => addNum(".")}>
            .
          </button>
          <button className="operator equal" onClick={equalFunction}>
            =
          </button>
        </div>
      </div>
    </div>
  );
}

export default Calculator;
