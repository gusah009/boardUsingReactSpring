import logo from "./logo.svg";
import List from "./list/List";
import { useEffect, useState } from "react";

function App() {
  // const [message, setMessage] = useState([]);
  // useEffect(() => {
  //   fetch("/hello")
  //     .then((response) => {
  //       return response.json();
  //     })
  //     .then(function (data) {
  //       setMessage(data);
  //     });
  // }, []);

  return (
    <>
      <List />
    </>
  );
}

export default App;
