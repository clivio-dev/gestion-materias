import React from "react";
import { PageBlock, Page } from "./types";

interface Props{
    home: PageBlock,
    routes: Page[],
    footer: PageBlock
}

const Nav: React.FC<Props> = ({ home, routes, footer }) =>  {
    return(
        <nav className="w-screen">
            <ul className="w-100 p-5 flex align-center justify-evenly bg-gray-900 text-white">
                <li
                    className=""
                >
                    <a 
                        className=""
                        href={home.id}
                    >
                        {home.content}
                    </a>
                </li>
                {routes && routes.map((p,i) => {
                    return(
                        <li 
                            className=""
                            key={i}
                        >
                            <a 
                                href={p.url.href} 
                                target="_blank" 
                                rel="noopener noreferrer"
                            >
                                {p.content}
                            </a>
                        </li>
                    )})
                }
                <li className="">
                    <a href={footer.id}>{footer.content}</a>
                </li>
            </ul>
        </nav>
    );
}

export default Nav;