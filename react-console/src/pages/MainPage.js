import * as React from 'react';
import { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from "@mui/material/Button";
import { Paper, Stack } from "@mui/material";

const MainPage = () => {
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [recipes, setRecipes] = useState([]);

    const handleSubmitClick = (e) => {
        e.preventDefault();
        const recipe = {name, price};
        fetch("http://localhost:8080/recipe/add", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(recipe)
        }).then(() => {
            setName('');
            setPrice('');
        })
    }

    useEffect(() => {
        fetch("http://localhost:8080/recipe/get-all")
            .then((res) => res.json())
            .then(result => setRecipes(result))
    }, [])

    return (
        <Stack display={'flex'} direction={'column'} alignItems={'center'}>
            <Box
                component="form"
                sx={{
                    '& > :not(style)': {m: 1},
                }}
                noValidate
                autoComplete="off"
            >
                <TextField id="recipe-name" label="Recipe Name" variant="standard" value={name}
                           onChange={e => setName(e.target.value)}/>
                <TextField id="recipe-price" label="Recipe Price" variant="standard" type={'number'} value={price}
                           onChange={e => setPrice(e.target.value)}/>
            </Box>
            <Button color="success" variant="contained" sx={{width: 100, mt: 1}}
                    onClick={handleSubmitClick}>Submit</Button>
            <Paper elevation={2} sx={{mt: 4, width: 600}}>
                {recipes.map(recipe => (
                    <Paper elevation={4} sx={{margin: 3, padding: 3, textAlign: 'left'}} key={recipe.id}>
                        ID: {recipe.id} <br/>
                        Name: {recipe.name}<br/>
                        Price: ${recipe.price}<br/>
                    </Paper>
                ))}
            </Paper>
        </Stack>
    );
}

export default MainPage;