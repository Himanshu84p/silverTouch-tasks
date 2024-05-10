import React from "react";
import ballonImg from "../assets/ballon.jpg";

function Intro({headLine, textColor}) {
  return (
    <div>
      <div className="flex m-20 justify-center items-center">
        
        {/* home ballon image */}
        <img
          src={ballonImg}
          className="rounded-md object-cover max-h-96 max-w-7xl shadow-2xl hover:scale-105 transform transition duration-500"
          alt="ballon image"
        />
        <h1 className={`${textColor} from-teal-800 text-5xl mt-6 mb-6 text-teal-600 text-left pl-10`}>
          {headLine}
        </h1>
      </div>
    </div>
  );
}

export default Intro;
