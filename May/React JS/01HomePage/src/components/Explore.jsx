import React from 'react'
import oceanImage from "../assets/oceanImage.avif"
import resortImage from "../assets/resort.avif"
import desertImage from "../assets/desert.avif"

export default function Explore({textColor}) {
  return (
    <section>
      <div className="mx-auto max-w-7xl px-2 lg:px-0 mt-8 mb-8">
        <div className="mx-auto max-w-3xl text-center">
          <h2 className={`${textColor} text-3xl font-semibold leading-tight {} sm:text-4xl lg:text-5xl lg:leading-tight`}>
            More fun in Vacation 
          </h2>
          <p className="mx-auto mt-4 max-w-2xl text-xl text-gray-600">
            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ullam reiciendis a vel error
            explicabo voluptatum nihil possimus veritatis eos culpa.
          </p>
        </div>

        {/* explore section cards */}
        <div className="mt-8 grid grid-cols-1 gap-4 sm:grid-cols-3 md:mt-16 lg:gap-x-12">
          <div>
            <img
              className="h-[500px] w-full rounded-md object-cover shadow-lg hover:scale-95 transform translate duration-300"
              src={oceanImage}
              alt=""
            />
          </div>
          <div>
            <img
              className="h-[500px] w-full rounded-md object-cover shadow-lg hover:scale-95 transform translate duration-300"
              src={resortImage}
              alt=""
            />
          </div>
          <div>
            <img
              className="h-[500px] w-full rounded-md object-cover shadow-lg hover:scale-95 transform translate duration-300"
              src={desertImage}
              alt=""
            />
          </div>
        </div>
        <div className="mt-8 text-center md:mt-16">
          <button
            type="button"
            className="rounded-md bg-teal px-3 py-2 text-sm font-semibold text-white shadow-sm hover:dark_slate_gray focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-black hover:scale-105 transform translate duration-300"
          >
            Explore More
          </button>
        </div>
      </div>
    </section>
  )
}
