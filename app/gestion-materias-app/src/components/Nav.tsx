import React from "react";

//type DOMAIN = "gestion-materias.com.ar";

type id = `#${string}`;

// Cambiar nombre de PageBlock por algo mas entendible
interface PageBlock{
    id: id,
    content: string
}

type url = string //`https://${DOMAIN}/${string}`;

interface Page{
    url: url,
    content: string
}

interface Props{
    home: PageBlock,
    routes: Page[],
    footer: PageBlock
}

const Nav : React.FC<Props> = ({ home, routes, footer }) =>  {
    return(
        <>
            <ul className="md:flex bg-gray-900">
                <li
                    className=""
                >
                    <a 
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
                                href={p.url} 
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
        </>
    );
}

export default Nav;