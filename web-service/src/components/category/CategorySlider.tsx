import React from "react";
import {clsx} from "clsx";
import {CategoryCard} from "~/components/category/CategoryCard";
// --------------------------------------------------------------------------
// XXX CategorySliderItem
// --------------------------------------------------------------------------
type CategorySliderItemProps = {
    className?: string,
    image: string,
    name: string,
}

const CategorySliderItem = React.forwardRef<HTMLAnchorElement, CategorySliderItemProps>(
    (props, ref) => {
        let className = props.className;
        if (!className) {
            className = "";
        }
        className = clsx(className, "flex flex-none mr-3 items-center justify-center text-center group")

        return (
            <CategoryCard ref={ref}
                          name={props.name}
                          image={props.image}
                          className={className}/>
        )
    });
CategorySliderItem.displayName = "CategorySliderItem";

// --------------------------------------------------------------------------
// XXX CategorySlider
// --------------------------------------------------------------------------

type CategorySliderProps = {
    className?: string,
    categories: CategorySliderItemProps[],
}

const CategorySlider = React.forwardRef<HTMLDivElement, CategorySliderProps>(
    (props, ref) => {
        let className = props.className;
        if (!className) {
            className = "";
        }
        className = clsx(className, "flex overflow-x-scroll")
        return (
            <div ref={ref} className={className}>
                {
                    props.categories.map(({name, image}, index) => (
                        <CategorySliderItem name={name}
                                            image={image}
                                            key={index}/>
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