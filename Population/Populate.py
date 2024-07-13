from sqlalchemy import create_engine
import pandas as pd
def Populate():
    db_user = "root"
    db_password = "Heggi_2002"
    db_host = "localhost"
    db_port = "3306"
    db_name = "Fasa7ni"

    engine_str = f"mysql+pymysql://{db_user}:{db_password}@{db_host}:{db_port}/{db_name}"
    engine = create_engine(engine_str)

    Names = []
    CSV = []
    for i in range(len(CSV)):
        df = pd.read_csv(CSV[i], encoding="utf-8")
        df = df.where(pd.notna(df), None)
        df.to_sql(Names[i], con=engine, if_exists="append", index=False)

    engine.dispose()

Populate()
