import React from "react";

export function NewsLetter() {
    return (
        <>
            <div className="hero bg-red-50 rounded mt-5 h-[48rem]"
                 style={{backgroundImage: 'url(/newsletter.jpg)'}}>
                <div className="hero-overlay bg-opacity-60"></div>
                <div className="hero-content">
                    <div className="max-w-md">
                        <div className="text-neutral-content text-center">
                            <p className="mb-5 text-3xl md:text-5xl font-bold">
                                Delicious Recipes
                            </p>
                            <p className="mb-5">
                                Enjoy the best recipes shared by people all over the world.
                            </p>
                        </div>

                        <input type="text"
                               placeholder="Email Address"
                               className="input input-bordered w-full mb-3"/>
                        <button className="btn btn-primary w-full text-lg">Join</button>
                    </div>
                </div>
            </div>

        </>
    )
}