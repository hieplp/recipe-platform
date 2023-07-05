import React from "react";
import Image from "next/image";
import {clsx} from "clsx"

// --------------------------------------------------------------------------
// XXX Carousel
// --------------------------------------------------------------------------
type CarouselProps = {
    children?: React.ReactNode,
    className?: string,
    items: CarouselItemProps[],
}

const Carousel = React.forwardRef<HTMLDivElement, CarouselProps>(
    ({className, items}, ref) => {
        return (
            <>
                <div ref={ref}
                     className={clsx(className, "relative overflow-hidden rounded-lg")}>
                    {/* Carousel items */}
                    {items.map(({image, alt}) => (
                        <CarouselItem key={image}
                                      image={image}
                                      alt={alt}/>
                    ))}

                    {/* Carousel controls */}
                    <SliderControllerPrev/>
                    <SliderControllerNext/>

                    {/* Carousel indicators */}
                    <div className="absolute z-30 flex space-x-3 -translate-x-1/2 bottom-5 left-1/2">
                        {items.map((item, index) => (
                            <SliderIndicator key={index}
                                             label={item.image}
                                             slideTo={index}
                                             className="bg-gray-100 dark:bg-gray-800"
                            />
                        ))}
                    </div>

                </div>
            </>
        )
    });
Carousel.displayName = "Carousel";

// --------------------------------------------------------------------------
// XXX CarouselItem
// --------------------------------------------------------------------------

type CarouselItemProps = {
    children?: React.ReactNode,
    image: string,
    alt?: string,
};

const CarouselItem = React.forwardRef<HTMLDivElement, CarouselItemProps>(
    ({
         children,
         image,
         alt
     }, ref) => {
        return (
            <>
                <div ref={ref}
                     className="duration-700 z-1
                                ease-in-out"
                     data-carousel-item="">
                    {children}
                    <Image src={image}
                           width={32}
                           height={32}
                           className="absolute block w-full
                                      -translate-x-1/2
                                      -translate-y-1/2
                                      top-1/2
                                      left-1/2"
                           alt={alt != null ? alt : ""}/>
                </div>
            </>
        )
    });
CarouselItem.displayName = "CarouselItem";

// --------------------------------------------------------------------------
// XXX SliderController
// --------------------------------------------------------------------------
type SliderControllerProps = {
    children?: React.ReactNode,
    className?: string,
};
const SliderController = React.forwardRef<HTMLButtonElement, SliderControllerProps>(
    ({
         children,
         className
     }, ref) => {
        if (!className) {
            className = "";
        }
        className = className
            + " absolute"
            + " z-30"
            + " flex"
            + " items-center justify-center"
            + " h-full"
            + " px-4"
            + " cursor-pointer"
            + " group"
            + " focus:outline-none";

        return (
            <button ref={ref}
                    type="button"
                    className={className}
                    data-carousel-prev="">
                <div className="inline-flex
                            items-center justify-center
                            w-10 h-10
                            rounded-full
                            bg-white/30
                            dark:bg-gray-800/30
                            group-hover:bg-white/50
                            dark:group-hover:bg-gray-800/60
                            group-focus:ring-4
                            group-focus:ring-white
                            dark:group-focus:ring-gray-800/70
                            group-focus:outline-none">
                    {children}
                </div>
            </button>
        )
    });
SliderController.displayName = "SliderController";

// --------------------------------------------------------------------------
// XXX SliderController - Prev
// --------------------------------------------------------------------------

const SliderControllerPrev = React.forwardRef<HTMLButtonElement>(
    ({}, ref) => {
        return (
            <SliderController ref={ref} className="top-0 left-0">
                <svg className="w-4 h-4 text-white dark:text-gray-800"
                     aria-hidden="true"
                     xmlns="http://www.w3.org/2000/svg"
                     fill="none" viewBox="0 0 6 10">
                    <path stroke="currentColor"
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="M5 1 1 5l4 4"/>
                </svg>
                <span className="sr-only">Previous</span>
            </SliderController>
        )
    });
SliderControllerPrev.displayName = "SliderControllerPrev";

// --------------------------------------------------------------------------
// XXX SliderController - Next
// --------------------------------------------------------------------------
const SliderControllerNext = React.forwardRef<HTMLButtonElement>(
    ({}, ref) => {
        return (
            <SliderController ref={ref} className="top-0 right-0">
                <svg className="w-4 h-4 text-white dark:text-gray-800" aria-hidden="true"
                     xmlns="http://www.w3.org/2000/svg"
                     fill="none" viewBox="0 0 6 10">
                    <path stroke="currentColor"
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          strokeWidth="2"
                          d="m1 9 4-4-4-4"/>
                </svg>
                <span className="sr-only">Next</span>
            </SliderController>
        )
    });
SliderControllerNext.displayName = "SliderControllerNext";


// --------------------------------------------------------------------------
// XXX SliderIndicator
// --------------------------------------------------------------------------
type SliderIndicatorProps = {
    children?: React.ReactNode,
    label: string,
    isActive?: boolean,
    slideTo: number,
    className?: string,
};

const SliderIndicator = React.forwardRef<HTMLButtonElement, SliderIndicatorProps>(
    ({
         label,
         isActive,
         slideTo,
         className
     }, ref) => {
        return (
            <button ref={ref}
                    type="button"
                    className={clsx(className, "w-3 h-3 rounded-full")}
                    aria-current={isActive}
                    aria-label={label}
                    data-carousel-slide-to={slideTo}></button>
        )
    });
SliderIndicator.displayName = "SliderIndicator";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {Carousel, CarouselItem, SliderController, SliderIndicator};
