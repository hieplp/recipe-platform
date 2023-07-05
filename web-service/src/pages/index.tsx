import {Layout} from "~/components/layouts/Layout";
import {Carousel} from "~/components/ui/Carousel";

export default function Home() {


    const corouselItems = [
        {
            image: "https://flowbite.com/docs/images/carousel/carousel-1.svg"
        },
        {

            image: "https://flowbite.com/docs/images/carousel/carousel-2.svg"
        }
    ];


    return (
        <>
            <Layout>

                <div className="relative w-full">
                    <Carousel items={corouselItems} className="h-56 md:h-96"/>
                </div>

            </Layout>
        </>
    );
}
