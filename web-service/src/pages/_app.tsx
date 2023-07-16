import {type AppType} from "next/dist/shared/lib/utils";
import "~/styles/globals.css";
import 'react-loading-skeleton/dist/skeleton.css'


const MyApp: AppType = ({Component, pageProps}) => {
    return <Component {...pageProps}/>;
};

export default MyApp;
