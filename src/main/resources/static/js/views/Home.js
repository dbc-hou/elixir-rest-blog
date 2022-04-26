export default function Home(props) {
    console.log("The frontend did it. HER FAULT");
    return `
        <header>
            <h1>Blog-o-Rama!</h1>
        </header>
        <main>
            <div>
                <p>
                    Click <strong>Register</strong> if you do not already have an account. Only one account per email address, y'all.
                </p>
                <p>
                    Otherwise, click <strong>Login</strong> and begin blogging your booty off!
                </p>
            </div>
        </main>
    `;
}