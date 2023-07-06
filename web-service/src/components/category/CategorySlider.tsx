import React from "react";
import Link from "next/link";
import {clsx} from "clsx";
import NextImage from "~/components/ui/NextImage";
// --------------------------------------------------------------------------
// XXX CategorySliderItem
// --------------------------------------------------------------------------
type CategorySliderItemProps = {
    className?: string,
    image: string,
    name: string,
}

const CategorySliderItem = React.forwardRef<HTMLAnchorElement, CategorySliderItemProps>(
    ({
         className,
         image,
         name,
     }, ref) => {
        if (!className) {
            className = "";
        }
        className = clsx(className, "flex flex-none mr-3 items-center justify-center text-center group")

        return (
            <>
                <Link href={`/categories/${name}`}
                      ref={ref}
                      passHref={true}
                      className={className}>
                    <div>
                        <NextImage width={1}
                                   height={1}
                                   useSkeleton={true}
                                   className="w-28 h-28
                                              md:w-40 md:h-40
                                              lg:w-48 lg:h-48
                                              rounded-2xl
                                              md:rounded-2xl"
                                   imgClassName="h-full w-full
                                                 rounded-2xl
                                                 md:rounded-2xl"
                                   src={image}
                                   alt={""}/>
                        <p className="text-center
                                      font-bold
                                      group-hover:text-blue-600
                                      mt-3">
                            {name}
                        </p>
                    </div>
                </Link>
            </>
        )
    });
CategorySliderItem.displayName = "CategorySliderItem";

// --------------------------------------------------------------------------
// XXX CategorySlider
// --------------------------------------------------------------------------

type CategorySliderProps = {
    className?: string,
    items: CategorySliderItemProps[],
}

const CategorySlider = React.forwardRef<HTMLDivElement, CategorySliderProps>(
    ({
         className,
         items,
     }, ref) => {
        if (!className) {
            className = "";
        }
        className = clsx(className, "flex overflow-x-scroll")
        return (
            <div ref={ref} className={className}>
                {
                    items.map(({name, image}, index) => (
                        <CategorySliderItem name={name}
                                            image={image}
                                            key={index}
                        />
                    ))
                }
            </div>
        )
    });
CategorySlider.displayName = "CategorySlider";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {CategorySliderItem, CategorySlider};