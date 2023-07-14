import React from "react";
import {clsx} from "clsx";
import {LineBreak} from "~/components/ui/Line";

// --------------------------------------------------------------------------
// XXX Title
// --------------------------------------------------------------------------
type TitleProps = {
    children?: React.ReactNode;
}

const Title = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & TitleProps
>(({
       className,
       ...props
   }, ref) => (
    <div ref={ref}
         className={clsx(className, "space-y-2")}>
        <p className="text-3xl font-bold">
            {props.children}
        </p>
        <LineBreak/>
    </div>
));
Title.displayName = "Title";

// --------------------------------------------------------------------------
// XXX Title - Subtitle
// --------------------------------------------------------------------------
const SubTitle = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement> & TitleProps
>(({
       className,
       ...props
   }, ref) => (
    <div ref={ref}
         className={clsx(className, "space-y-2")}>
        <p className="text-md -bold">
            {props.children}
        </p>
        <LineBreak/>
    </div>
));
SubTitle.displayName = "SubTitle";

// --------------------------------------------------------------------------
// XXX Export
// --------------------------------------------------------------------------
export {
    Title,
    SubTitle
}